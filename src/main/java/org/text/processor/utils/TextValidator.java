package org.text.processor.utils;

public class TextValidator {
    public boolean isByteExpression(String string) {
        return string.contains(">>");
    }

    public static boolean isDigit(char digit) {
        return Character.isDigit(digit);
    }

    public static boolean isNumber(String string) {
        for (int i = 0; i < string.length(); i++) {
            char negativeSign = '-';
            char currentChar = string.charAt(i);
            if (!isDigit(currentChar) || currentChar == negativeSign) {
                return false;
            }
        }
        return true;
    }

    public static boolean isContainSymbol(char symbol, String expression) {
        return expression.indexOf(symbol) != -1;
    }
}
