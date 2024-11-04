package org.text.processor.utils;

import org.text.processor.action.interpretator.Expression;
import org.text.processor.action.interpretator.ExpressionEvaluator;
import org.text.processor.action.interpretator.ExpressionParser;

public class TextUtils {

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

    public static int getEvaluatedExpression(String expression) {
        ExpressionParser expressionParser = new ExpressionParser();
        expressionParser.parse(expression);
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(expressionParser);
        Expression result = expressionEvaluator.getCombinedExpression();
        return result.interpret();
    }
}
