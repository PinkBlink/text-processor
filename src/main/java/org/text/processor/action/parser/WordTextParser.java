package org.text.processor.action.parser;

import org.text.processor.action.interpretator.Expression;
import org.text.processor.action.interpretator.ExpressionEvaluator;
import org.text.processor.action.interpretator.ExpressionParser;
import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.TextSegment;
import org.text.processor.entity.Word;
import org.text.processor.utils.TextUtils;
import org.text.processor.utils.TextValidator;

public class WordTextParser extends TextParser {
    @Override
    public void parse(TextSegment textSegment, String text) {
        Sentence sentenceSegment = (Sentence) textSegment;
        String[] words = text.split(TextConstants.SPACE_SEPARATOR);

        for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {
            String wordString = words[i];
            int lastIndex = wordsLength - 1;
            if (i == lastIndex) {
                String[] separatedWord = TextUtils.getSeparatedWordFromFinalPunctuation(wordString);
                wordString = separatedWord[0];
                String finalPunctuation = separatedWord[1];
                sentenceSegment.setFinalPunctuation(finalPunctuation);
            }
            if (TextValidator.isValidExpression(wordString)) {
                wordString = String.valueOf(TextUtils.getEvaluatedExpression(wordString));
            }
            if (wordString.isEmpty()) {
                continue;
            }
            Word wordSegment = new Word(wordString.trim());
            sentenceSegment.addWord(wordSegment);
        }
    }

}
