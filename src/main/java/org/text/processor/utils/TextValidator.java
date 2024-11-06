package org.text.processor.utils;

import org.text.processor.constants.TextConstants;

import java.util.regex.Pattern;

public class TextValidator {
    public static boolean isDigit(char digit) {
        return Character.isDigit(digit);
    }

    public static boolean isNumber(String string) {
        if (string.isEmpty()) {
            return false;
        }
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

    public static boolean hasPunctuation(String word) {
        return Pattern.matches(TextConstants.PUNCTUATION_REGEX, word);
    }
}