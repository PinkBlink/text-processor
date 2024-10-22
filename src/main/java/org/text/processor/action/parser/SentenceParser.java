package org.text.processor.action.parser;

import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.TextSegment;

public class SentenceParser extends Parser {
    @Override
    public void parse(TextSegment textSegment, String text) {
        Paragraph paragraphSegment = (Paragraph) textSegment;
        String[] sentences = text.split(TextConstants.SENTENCE_SEPARATOR_REGEX);

        for (String sentenceString : sentences) {
            Sentence sentenceSegment = new Sentence();
            paragraphSegment.addSentence(sentenceSegment);
            if (nextParser != null) {
                nextParser.parse(sentenceSegment, sentenceString);
            }
        }

    }
}
