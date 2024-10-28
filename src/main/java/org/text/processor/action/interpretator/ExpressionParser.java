package org.text.processor.action.interpretator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.text.processor.exception.IllegalExpressionException;
import org.text.processor.utils.BitwiseOperationUtils;
import org.text.processor.utils.TextValidator;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    //(7^5|1&2<<(2|5>>2&71))|1200
    // 1)  "2 | 5 >> 2 & 71" :

    // 1.1) 5 >> 2
    // 1.2) [1.1] & 71
    // 1.3) 2 | [1.2]

    //2) 7 ^ 5 | 1 & 2<<[1.3] :

    //2.1) 2 << [1.3]
    //2.2) 1 & [2.1]
    //2.3) 7 ^ 5
    //2.4) [2.3] | [2.2]

    //3) [2.4] | 1200
    private Logger logger = LogManager.getLogger(this.getClass());
    private List<Expression> numberExpressionList = new ArrayList<>();
    private final List<BitwiseOperator> operationExpressionsList = new ArrayList<>();

    private final String stringExpression;

    public ExpressionParser(String stringExpression) {
        if (TextValidator.isValidExpression(stringExpression)) {
            this.stringExpression = stringExpression;
        } else {
            logger.log(Level.ERROR, "Wrong expression: " + stringExpression);
            throw new IllegalArgumentException("Wrong expression: " + stringExpression);
        }
    }

    private void parse() {
        //~ 2 | 5 >> ~ 2 & 71
        //0 1 2 3 45 6 7 8 9  <<indices

        // ~2 5 ~2 71
        // 0 1 2 3    <<indices

        // | > &
        // 0 1 2    <<indices

        // vv

        // | > &
        // 0 1 2     <<indices

        int index = 0;
        int length = stringExpression.length();
        while (index < length) {
            char currentChar = stringExpression.charAt(index);
            if (TextValidator.isDigit(currentChar)) {
                int endIndex = getIndexNumberFrom(index, stringExpression);
                int number = getNumber(index, endIndex, stringExpression);
                Expression numberExpression = new NumberExpression(number);
                addNumberExpressionToList(numberExpression);
                index = endIndex;
            } else {
                BitwiseOperator operator = BitwiseOperationUtils.getBitwiseOperator(currentChar);
                if (operator.equals(BitwiseOperator.NOT)) {
                    int endIndex = getIndexNumberFrom(index, stringExpression);
                    int number = getNumber(index, endIndex, stringExpression);
                    Expression numberExpression = new NumberExpression(number);
                    Expression notExpression = new NotExpression(numberExpression);
                    addNumberExpressionToList(notExpression);
                } else {
                    if (operator.equals(BitwiseOperator.LEFT_SHIFT)
                            || operator.equals(BitwiseOperator.RIGHT_SHIFT)) {
                        index++;
                    } else {
                        addOperationToList(operator);
                    }
                }
                index++;
            }
        }
    }


    private void addOperationToList(BitwiseOperator operator) {
        operationExpressionsList.add(operator);
    }

    private void addNumberExpressionToList(Expression numberExpression) {
        numberExpressionList.add(numberExpression);
    }

    private int getIndexNumberFrom(int startIndex, String expression) {
        //~2<<2;
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
        this.parse();
        while (numberExpressionList.size() > 1) {

        }
        return numberExpressionList.getFirst();
    }

    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser("2|5>>2&71");
        parser.parse();
        System.out.println(parser.numberExpressionList);
        System.out.println(parser.operationExpressionsList);
    }
}
























