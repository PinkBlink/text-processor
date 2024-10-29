package org.text.processor.action.interpretator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.text.processor.constants.TextConstants;
import org.text.processor.exception.IllegalExpressionException;
import org.text.processor.utils.BitwiseOperationUtils;
import org.text.processor.utils.TextValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ExpressionParser {
    private Logger logger = LogManager.getLogger(this.getClass());
    private List<Expression> numberExpressionList = new ArrayList<>();
    private final List<BitwiseOperator> operationExpressionsList = new ArrayList<>();

    private int parse(String stringExpression, int startIndex) {
        int index = startIndex;
        int length = stringExpression.length();

        while (index < length) {
            char currentChar = stringExpression.charAt(index);
            if (currentChar == TextConstants.LEFT_BRACKET) {
                int innerIndex = index + 1;
                ExpressionParser subParser = new ExpressionParser();
                int lastBracketIndex = subParser.parse(stringExpression, innerIndex);
                Expression bracketExpression = subParser.getTheCollectedExpression();
                addNumberExpressionToList(bracketExpression);
                index = lastBracketIndex;
                if (index == length) {
                    break;
                }
                currentChar = stringExpression.charAt(index);

            }
            if (currentChar == TextConstants.RIGHT_BRACKET) {
                return index + 1;
            }
            if (TextValidator.isDigit(currentChar)) {
                int endIndex = getLastIndexNumberFrom(index, stringExpression);
                int number = getNumber(index, endIndex, stringExpression);
                Expression numberExpression = new NumberExpression(number);
                addNumberExpressionToList(numberExpression);
                index = endIndex;
            } else {
                BitwiseOperator operator = BitwiseOperationUtils.getBitwiseOperator(currentChar);
                if (operator.equals(BitwiseOperator.NOT)) {
                    int endIndex = getLastIndexNumberFrom(index, stringExpression);
                    int number = getNumber(index + 1, endIndex, stringExpression);
                    Expression numberExpression = new NumberExpression(number);
                    Expression notExpression = new NotExpression(numberExpression);
                    addNumberExpressionToList(notExpression);
                } else {
                    if (operator.equals(BitwiseOperator.LEFT_SHIFT)
                            || operator.equals(BitwiseOperator.RIGHT_SHIFT)) {
                        index++;
                    }
                    addOperationToList(operator);
                }
                index++;
            }
        }
        return index;
    }

    private void addOperationToList(BitwiseOperator operator) {
        operationExpressionsList.add(operator);
    }

    private void addNumberExpressionToList(Expression numberExpression) {
        numberExpressionList.add(numberExpression);
    }

    private int getLastIndexNumberFrom(int startIndex, String expression) {
        int endIndex = startIndex + 1;
        while (endIndex < expression.length()
                && TextValidator.isDigit(expression.charAt(endIndex))) {
            endIndex++;
        }
        return endIndex;
    }

    private int getNumber(int fromIndex, int toIndex, String stringExpression) {
        String potentialNumber = stringExpression.substring(fromIndex, toIndex);
        if (TextValidator.isNumber(potentialNumber)) {
            return Integer.parseInt(potentialNumber);
        } else {
            throw new IllegalExpressionException("String " + potentialNumber + " is not a number");
        }
    }

    public Expression getTheCollectedExpression() {
        for (int i = TextConstants.MAX_BITWISE_OPERATOR_PRIORITY; i > 0; i--) {
            int indexOfOperator = getFirstIndexOfOperationInPriority(i);
            while (indexOfOperator != -1) {
                mergeExceptionWithOperator(indexOfOperator);
                indexOfOperator = getFirstIndexOfOperationInPriority(i);
            }
        }
        return numberExpressionList.getFirst();
    }

    private void mergeExceptionWithOperator(int operatorIndex) {
        BitwiseOperator operator = operationExpressionsList.get(operatorIndex);
        Expression firstOperand = numberExpressionList.get(operatorIndex);
        Expression secondOperand = numberExpressionList.get(operatorIndex + 1);
        Expression resultExpression = operator.getExpression(firstOperand, secondOperand);
        numberExpressionList.remove(operatorIndex + 1);
        numberExpressionList.set(operatorIndex, resultExpression);
        operationExpressionsList.remove(operatorIndex);
    }

    private int getFirstIndexOfOperationInPriority(int priority) {
        return IntStream.range(0, operationExpressionsList.size())
                .filter(i -> operationExpressionsList.get(i).getPriority() == priority)
                .findFirst()
                .orElse(-1);
    }

    public static void main(String[] args) {
        ExpressionParser expressionParser = new ExpressionParser();
        expressionParser.parse("(7^5|1&2<<(2|5>>2&71))|1200", 0);
        Expression expression = expressionParser.getTheCollectedExpression();
        System.out.println("should be:");
        System.out.println((7^5|1&2<<(2|5>>2&71))|1200);
        System.out.println("output:");
        System.out.println(expression.interpret());
    }
}