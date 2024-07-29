package org.text.processor.action;

import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.TextSegment;
import org.text.processor.entity.Word;

public class WordParser extends Parser {
    @Override
    public void parse(TextSegment textSegment, String text) {
        Sentence sentenceSegment = (Sentence) textSegment;
        String[] words = text.split(TextConstants.SPACE_SEPARATOR);

        for (String wordString : words) {
            Word wordSegment = new Word(wordString.trim());
            sentenceSegment.addWord(wordSegment);
        }
    }
}
