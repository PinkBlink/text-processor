package org.text.processor.utils;

import org.text.processor.action.interpretator.Expression;
import org.text.processor.action.interpretator.ExpressionEvaluator;
import org.text.processor.action.interpretator.ExpressionParser;
import org.text.processor.action.parser.ParagraphTextParser;
import org.text.processor.action.parser.SentenceTextParser;
import org.text.processor.action.parser.WordTextParser;
import org.text.processor.entity.Text;
import org.text.processor.entity.Word;

public class TextUtils {

    public static int getPunctuationStartIndex(String word) {
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            char currentChar = letters[i];
            if (currentChar == '.' || currentChar == '?' || currentChar == '!' || currentChar == ',') {
                return i;
            }
        }
        return -1;
    }

    public static String[] getSeparatedWordFromPunctuation(String word) {
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
    public static Text getParsedText(String text) {
        ParagraphTextParser paragraphTextParser = new ParagraphTextParser();
        SentenceTextParser sentenceTextParser = new SentenceTextParser();
        WordTextParser wordTextParser = new WordTextParser();
        paragraphTextParser.setNextParser(sentenceTextParser);
        sentenceTextParser.setNextParser(wordTextParser);
        Text actual = new Text();
        paragraphTextParser.parse(actual, text);
        return actual;
    }
    public static int getAmountOfSymbolInWord(Word word, char symbol) {
        return (int) word.getContent().chars().filter(s -> s == symbol).count();
    }
}
