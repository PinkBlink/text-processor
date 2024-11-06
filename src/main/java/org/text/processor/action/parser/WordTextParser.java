package org.text.processor.action.parser;

import org.text.processor.constants.TextConstants;
import org.text.processor.entity.PunctuationMark;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.TextSegment;
import org.text.processor.entity.Word;
import org.text.processor.exception.IllegalStringException;
import org.text.processor.utils.TextUtils;
import org.text.processor.utils.TextValidator;

public class WordTextParser extends TextParser {
    @Override
    public void parse(TextSegment textSegment, String text) {
        if (text.isEmpty()) {
            throw new IllegalStringException("Trying to parse an empty string.");
        }
        Sentence sentenceSegment = (Sentence) textSegment;
        String[] words = text.split(TextConstants.SPACE_SEPARATOR);

        for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {
            String wordString = words[i];
            if (wordString.isEmpty()) {
                continue;
            }
            if (TextValidator.hasPunctuation(wordString)) {
                String[] separatedWord = TextUtils.getSeparatedWordFromPunctuation(wordString);
                wordString = separatedWord[0];
                PunctuationMark punctuationMark = new PunctuationMark(separatedWord[1], i);
                sentenceSegment.addPunctuation(punctuationMark);
            }
            if (TextValidator.isValidExpression(wordString)) {
                wordString = String.valueOf(TextUtils.getEvaluatedExpression(wordString));
            }
            Word wordSegment = new Word(wordString.trim());
            sentenceSegment.addWord(wordSegment);
        }
    }
}