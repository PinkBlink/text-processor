package org.text.processor.action.interpretator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.text.processor.constants.TextConstants;
import org.text.processor.exception.IllegalExpressionException;
import org.text.processor.utils.TextValidator;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private List<Expression> numberExpressionList = new ArrayList<>();
    private List<BitwiseOperator> operationExpressionsList = new ArrayList<>();

    public List<Expression> getNumberExpressionList() {
        return numberExpressionList;
    }

    public List<BitwiseOperator> getOperationExpressionsList() {
        return operationExpressionsList;
    }

    public void parse(String expression) {
        if (!numberExpressionList.isEmpty()
                || !operationExpressionsList.isEmpty()) {
            numberExpressionList.clear();
            operationExpressionsList.clear();
        }
        parse(expression, 0);
    }

    private int parse(String stringExpression, int startIndex) {
        int index = startIndex;
        int length = stringExpression.length();
        while (index < length) {
            char currentChar = stringExpression.charAt(index);
            if (currentChar == TextConstants.LEFT_BRACKET) {
                index = parseExpressionInBrackets(stringExpression, index);
                if (index == length) {
                    break;
                }
                currentChar = stringExpression.charAt(index);
            }
            if (currentChar == TextConstants.RIGHT_BRACKET) {
                return index + TextConstants.STEP;
            }
            if (TextValidator.isDigit(currentChar)) {
                index = addNumberToListWithIndexShift(stringExpression, index);
            } else {
                BitwiseOperator operator = BitwiseOperator.getBitwiseOperator(currentChar);
                switch (operator) {
                    case NOT -> {
                        index = addNotExpressionAndShift(stringExpression, index);
                    }
                    case LEFT_SHIFT, RIGHT_SHIFT -> {
                        index++;
                        addOperationToList(operator);
                    }
                    case XOR, OR, AND -> addOperationToList(operator);
                }
                index++;
            }
        }
        return index;
    }

    private int addNotExpressionAndShift(String stringExpression, int index) {
        int startNumberIndex = index + TextConstants.STEP;
        int indexAfterNumber = getNextIndexAfterNumber(index, stringExpression);
        int number = getNumber(startNumberIndex, indexAfterNumber, stringExpression);
        Expression numberExpression = new NumberExpression(number);
        Expression notExpression = new NotExpression(numberExpression);
        addNumberExpressionToList(notExpression);
        return indexAfterNumber - TextConstants.STEP;
    }

    private int parseExpressionInBrackets(String stringExpression, int index) {
        int innerIndex = index + TextConstants.STEP;
        ExpressionParser subParser = new ExpressionParser();
        int lastBracketIndex = subParser.parse(stringExpression, innerIndex);
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(subParser);
        Expression bracketExpression = expressionEvaluator.getCombinedExpression();
        addNumberExpressionToList(bracketExpression);
        return lastBracketIndex;
    }

    private int addNumberToListWithIndexShift(String stringExpression, int index) {
        int endIndex = getNextIndexAfterNumber(index, stringExpression);
        int number = getNumber(index, endIndex, stringExpression);
        Expression numberExpression = new NumberExpression(number);
        addNumberExpressionToList(numberExpression);
        return endIndex;
    }

    private void addOperationToList(BitwiseOperator operator) {
        operationExpressionsList.add(operator);
    }

    private void addNumberExpressionToList(Expression numberExpression) {
        numberExpressionList.add(numberExpression);
    }

    private int getNextIndexAfterNumber(int startIndex, String expression) {
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
}

