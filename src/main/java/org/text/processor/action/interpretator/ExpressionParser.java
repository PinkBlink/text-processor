package org.text.processor.action.interpretator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.text.processor.constants.TextConstants;
import org.text.processor.utils.TextValidator;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ExpressionFactory expressionFactory = new ExpressionFactoryImpl();
    private final List<Expression> numberExpressionList = new ArrayList<>();
    private final List<BitwiseOperator> operationExpressionsList = new ArrayList<>();

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
        int startIndex = 0;
        parse(expression, startIndex);
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
                index = addNumberToListWithIndexShiftIndex(stringExpression, index);
            } else {
                index = processBinaryOperatorAndShiftIndex(currentChar, index, stringExpression);
            }
        }
        return index;
    }

    private int processBinaryOperatorAndShiftIndex(char operatorChar, int index, String stringExpression) {
        BitwiseOperator operator = BitwiseOperator.getBitwiseOperator(operatorChar);
        switch (operator) {
            case NOT -> {
                index = addNotExpressionAndShift(stringExpression, index);
            }
            case LEFT_SHIFT, RIGHT_SHIFT -> {
                index++;
                addOperationToList(operator);
            }
            case XOR, OR, AND -> {
                addOperationToList(operator);
            }
        }
        index++;
        return index;
    }

    private int addNotExpressionAndShift(String stringExpression, int index) {
        int startNumberIndex = index + TextConstants.STEP;
        int indexAfterNumber = getNextIndexAfterNumber(index, stringExpression);
        int number = getNumber(startNumberIndex, indexAfterNumber, stringExpression);
        Expression numberExpression = expressionFactory.createNumberExpression(number);
        Expression notExpression = expressionFactory.createNotExpression(numberExpression);
        addNumberExpressionToList(notExpression);
        logger.log(Level.INFO, "add "+ notExpression + "to List;");

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

    private int addNumberToListWithIndexShiftIndex(String stringExpression, int index) {
        int endIndex = getNextIndexAfterNumber(index, stringExpression);
        int number = getNumber(index, endIndex, stringExpression);
        Expression numberExpression = expressionFactory.createNumberExpression(number);
        addNumberExpressionToList(numberExpression);
        logger.log(Level.INFO, "add "+ numberExpression + "to List;");
        return endIndex;
    }

    private void addOperationToList(BitwiseOperator operator) {
        operationExpressionsList.add(operator);
    }

    private void addNumberExpressionToList(Expression numberExpression) {
        numberExpressionList.add(numberExpression);
    }

    private int getNextIndexAfterNumber(int startIndex, String expression) {
        int endIndex = startIndex + TextConstants.STEP;
        while (endIndex < expression.length()
                && TextValidator.isDigit(expression.charAt(endIndex))) {

            endIndex++;
        }
        return endIndex;
    }

    private int getNumber(int fromIndex, int toIndex, String stringExpression) {
        String potentialNumber = stringExpression.substring(fromIndex, toIndex);
        TextValidator.throwExceptionIfNotNumber(potentialNumber);
        return Integer.parseInt(potentialNumber);
    }
}