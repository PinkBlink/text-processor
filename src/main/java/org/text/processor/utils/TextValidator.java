package org.text.processor.utils;

import org.text.processor.constants.TextConstants;
import org.text.processor.exception.IllegalExpressionException;

import java.util.regex.Pattern;

public class TextValidator {
    public static boolean isDigit(char digit) {
        return Character.isDigit(digit);
    }

    public static void throwExceptionIfNotNumber(String string) {
        IllegalExpressionException exception = new IllegalExpressionException("String  + " + string + " is not a number");
        if (string.isEmpty()) {
            throw exception;
        }
        for (int i = 0; i < string.length(); i++) {
            char negativeSign = '-';
            char currentChar = string.charAt(i);
            if (i == 0 && currentChar == negativeSign) {
                continue;
            }
            if (!isDigit(currentChar)) {
                throw exception;
            }
        }
    }

    public static boolean isValidExpression(String expression) {
        return !expression.isEmpty()
                && Pattern.matches(TextConstants.EXPRESSION_REGEX, expression);
    }

    public static boolean hasPunctuation(String word) {
        return Pattern.matches(TextConstants.PUNCTUATION_REGEX, word);
    }
}