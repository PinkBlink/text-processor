package org.text.processor.utils;

import org.text.processor.constants.TextConstants;

import java.util.regex.Pattern;

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
            if (i == 0 && currentChar == negativeSign) {
                continue;
            }
            if (!isDigit(currentChar)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidExpression(String expression) {
        return !expression.isEmpty()
                && Pattern.matches(TextConstants.EXPRESSION_REGEX, expression);
    }

    public static boolean isContainSymbol(char symbol, String expression) {
        return expression.indexOf(symbol) != -1;
    }
}
