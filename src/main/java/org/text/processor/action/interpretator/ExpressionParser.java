package org.text.processor.action.interpretator;

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
    //    private Logger logger = LogManager.getLogger(this.getClass());
    private List<Expression> numberExpressionList = new ArrayList<>();
    private final List<BitwiseOperator> operationExpressionsList = new ArrayList<>();

    public Expression getTheCollectedExpression() {
        mergeAllExpressions();
        if (numberExpressionList.size() == 1) {
            return numberExpressionList.getFirst();
        } else {
            System.err.println(this.operationExpressionsList + " <-- operation Expressions list");
            System.err.println(this.numberExpressionList + "<-- Numbers list");
            throw new IllegalExpressionException("Error in combining expressions, left :" + numberExpressionList.size());
        }
    }

    public void parse(String expression) {
        numberExpressionList.clear();
        operationExpressionsList.clear();
        parse(expression, 0);
    }

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
                    index = endIndex-1; //problem was here need step back
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

    private void mergeAllExpressions() {
        for (int i = TextConstants.MAX_BITWISE_OPERATOR_PRIORITY; i > 0; i--) {
            int indexOfOperator = getFirstIndexOfOperationInPriority(i);
            while (indexOfOperator != -1) {
                mergeExceptionWithOperator(indexOfOperator);
                indexOfOperator = getFirstIndexOfOperationInPriority(i);
            }
        }
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
        String expressionString1 = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
        String expressionString2 = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2)|78)";//
        String expressionString3 = "~6&9|(3&4)";
        String expressionString4 = "(7^5|1&2<<(2|5>>2&71))|1200";
        String expressionString5 = "13<<2";
        String expressionString6 = "3>>5";
        String expressionString7 = "(3|(2&1<<1))|4";
        String expressionString8 = "(2&3)|(4^1)";
        String expressionString9 = "~3&(2|1)";
        String expressionString10 = "~71&(2&3|3)|78";
        int expression1 = 5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1);
        int expression2 = (~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) & 2) | 10 & 2) | 78);  //
        int expression3 = ~6 & 9 | (3 & 4);
        int expression4 = (7 ^ 5 | 1 & 2 << (2 | 5 >> 2 & 71)) | 1200;
        int expression5 = 13 << 2;
        int expression6 = 3 >> 5;
        int expression7 = (3 | (2 & 1 << 1)) | 4;
        int expression8 = (2 & 3) | (4 ^ 1);
        int expression9 = ~3 & (2 | 1);
        testParser(expressionParser, expressionString1, expression1);
        testParser(expressionParser, expressionString2, expression2);
        testParser(expressionParser, expressionString3, expression3);
        testParser(expressionParser, expressionString4, expression4);
        testParser(expressionParser, expressionString5, expression5);
        testParser(expressionParser, expressionString6, expression6);
        testParser(expressionParser, expressionString7, expression7);
        testParser(expressionParser, expressionString8, expression8);
        testParser(expressionParser, expressionString9, expression9);
    }

    static void testParser(ExpressionParser parser, String expressionString, int expressionInt) {
        parser.parse(expressionString);
        Expression expression = parser.getTheCollectedExpression();
        System.out.println("-------------------");
        System.out.println("should be:");
        System.out.println(expressionInt);
        System.out.println("output:");
        System.out.println(expression.interpret());
        System.out.println("--------------");
    }
}