package org.text.processor.utils;

import org.text.processor.constants.TextConstants;

import java.util.regex.Pattern;

public class BitwiseOperationUtils {
    //~6&9|(3&4)
    public static String getSolvedExpression(String expression) {
        while (hasBrackets(expression)) {

        }
        return "need implementation";
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

    private static String replaceBracketToNumber(String expression, int leftBracketIndex, int rightBracketIndex) {
        int startIndex = leftBracketIndex + 1;
        String expressionFromBrackets = getExpressionFromBrackets(expression, leftBracketIndex, rightBracketIndex);
        return "need implementation";
    }

    private static String getExpressionFromBrackets(String expression, int leftBracketIndex, int rightBracketIndex) {
        int startIndex = leftBracketIndex + 1;
        return expression.substring(startIndex, rightBracketIndex);
    }

    private static int applyNot(String expression) {
        int operatorIndex = expression.indexOf("~");

        return 0;
    }

    private int getRightNumber(String expression, int operatorIndex) {
        StringBuilder result = new StringBuilder();
        int charIndex = operatorIndex + 1;
        char currentChar = expression.charAt(charIndex);

        while (charIndex < expression.length() && Character.isDigit(currentChar)) {
            result.append(currentChar);
            charIndex++;
        }
        return Integer.parseInt(result.toString());
    }

    private int getLeftNumber(String expression, int operatorIndex) {
        StringBuilder result = new StringBuilder();
        int charIndex = operatorIndex - 1;
        char currentChar = expression.charAt(charIndex);

        while (charIndex > -1 && Character.isDigit(currentChar)) {
            result.append(currentChar);
            charIndex--;
        }
        return Integer.parseInt(result.toString());
    }

    public static void main(String[] args) {
        String testExpression = "~6&9|(3&4)";
        String testBracketsIndices = "(7^5|1&2<<(2|5>>2&71))|1200";
        System.out.println(hasBrackets(testExpression));
        int[] indices = findInnermostBrackets(testBracketsIndices);
        int left = indices[0];
        int right = indices[1];

//        System.out.println("Should be 10 for left index and 20 for right index");
//        System.out.println("left-->" + indices[0] + " right-->" + indices[1]);
//        System.out.println(getExpressionFromBrackets(testBracketsIndices, left, right));
    }
}
