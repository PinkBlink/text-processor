package org.text.processor.utils;

import org.text.processor.action.interpretator.BitwiseOperator;
import org.text.processor.action.interpretator.Expression;
import org.text.processor.action.interpretator.NotExpression;
import org.text.processor.action.interpretator.NumberExpression;
import org.text.processor.constants.TextConstants;
import org.text.processor.exception.IllegalExpressionException;

import java.util.regex.Pattern;

public class BitwiseOperationUtils {

    //mb remove to test utils(need to create)
    public static String replace(String expression, int startIndex, int endIndex, String replacement) {
        String result = expression.substring(0, startIndex)
                .concat(replacement)
                .concat(expression.substring(endIndex));
        return result;
    }

    public static BitwiseOperator getBitwiseOperator(char operator) {
        return switch (operator) {
            case '~' -> BitwiseOperator.NOT;
            case '>' -> BitwiseOperator.RIGHT_SHIFT;
            case '<' -> BitwiseOperator.LEFT_SHIFT;
            case '^' -> BitwiseOperator.XOR;
            case '|' -> BitwiseOperator.OR;
            default -> throw new IllegalExpressionException("Wrong bitwise operation: " + operator);
        };
    }

    private static boolean hasBrackets(String expression) {
        return Pattern.compile(TextConstants.BRACKETS_REGEX).matcher(expression).find();
    }

    private static int[] findInnermostBrackets(String expression) {
        int rightBracket = getInnermostRightBracketIndex(expression);
        int leftBracket = getInnermostLeftBracketIndex(expression, rightBracket);
        return new int[]{leftBracket, rightBracket};
    }

    private static int getInnermostRightBracketIndex(String expression) {
        return expression.indexOf(")");
    }

    private static int getInnermostLeftBracketIndex(String expression, int rightBracketIndex) {
        char leftBracket = '(';
        int leftBracketIndex;
        int startIndex = rightBracketIndex - 1;
        for (int i = startIndex; i >= 0; i--) {
            char currentChar = expression.charAt(i);
            if (currentChar == leftBracket) {
                leftBracketIndex = i;
                return leftBracketIndex;
            }
        }
        return -1;
    }

    //!!!
    private static String replaceBracketToNumber(String expression, int leftBracketIndex, int rightBracketIndex) {
        int startIndex = leftBracketIndex + 1;
        String expressionFromBrackets = getExpressionFromBrackets(expression, leftBracketIndex, rightBracketIndex);
        return "need implementation";
    }

    private static String getExpressionFromBrackets(String expression, int leftBracketIndex, int rightBracketIndex) {
        int startIndex = leftBracketIndex + 1;
        return expression.substring(startIndex, rightBracketIndex);
    }

    private static int getRightNumber(String expression, int operatorIndex, boolean isShift) {
        if (isShift) {
            operatorIndex++;
        }
        int firstIndexOfNumber = operatorIndex + 1;
        int lastIndexOfNumber = getExtremeIndexOfRightNumber(expression, operatorIndex);
        return Integer.parseInt(expression.substring(firstIndexOfNumber, lastIndexOfNumber + 1));
    }

    private static int getLeftNumber(String expression, int operatorIndex) {
        int firstIndexOfNumber = getExtremeIndexOfLeftNumber(expression, operatorIndex);
        return Integer.parseInt(expression.substring(firstIndexOfNumber, operatorIndex));
    }

    public static int getExtremeIndexOfRightNumber(String expression, int operatorIndex) {
        int charIndex = operatorIndex + 1;
        char currentChar;
        while (charIndex < expression.length()) {
            currentChar = expression.charAt(charIndex);
            if (TextValidator.isDigit(currentChar) || currentChar == TextConstants.NEGATIVE_SIGN) {
                charIndex++;
            } else {
                break;
            }
        }
        return charIndex - 1;
    }

    public static int getExtremeIndexOfLeftNumber(String expression, int operatorIndex) {
        int charIndex = operatorIndex - 1;
        char currentChar;
        while (charIndex >= 0) {
            currentChar = expression.charAt(charIndex);
            if (TextValidator.isDigit(currentChar) || currentChar == TextConstants.NEGATIVE_SIGN) {
                charIndex--;
            } else {
                break;
            }
        }
        return charIndex + 1;
    }

    public static void main(String[] args) {
        String testExpression1 = "13<<2";
        String testExpression2 = "3>>5";
        String testExpression3 = "~6&9|(3&4)";
        String testExpression4 = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
        String testExpression5 = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
        String testExpression6 = "(7^5|1&2<<(2|5>>2&71))|1200";
        System.out.println(5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1));
    }
}
