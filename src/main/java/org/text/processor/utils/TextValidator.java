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

    public static int getPunctuationStartIndex(String word) {
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            char currentChar = letters[i];
            if (currentChar == '.' || currentChar == '?' || currentChar == '!') {
                return i;
            }
        }
        return -1;
    }

    public static String[] getSeparatedWordFromFinalPunctuation(String word) {
        int indexOfPunctuation = getPunctuationStartIndex(word);
        if (indexOfPunctuation == -1) {
            return new String[]{word, ""};
        }
        String[] result = new String[2];
        result[0] = word.substring(0, indexOfPunctuation);
        result[1] = word.substring(indexOfPunctuation);
        return result;
    }
}
