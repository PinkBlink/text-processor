package org.text.processor.action.parser;

import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Text;
import org.text.processor.entity.TextSegment;
import org.text.processor.exception.IllegalExpressionException;

public class ParagraphTextParser extends TextParser {
    @Override
    public void parse(TextSegment textSegment, String text) {
        if(text.isEmpty()){
            throw new IllegalExpressionException("Trying to parse an empty string.");
        }
        Text currentTextSegment = (Text) textSegment;
        String[] paragraphs = text.split(TextConstants.LINE_BREAK_SEPARATOR);

        for (String paragraphString : paragraphs) {
            Paragraph paragraphSegment = new Paragraph();
            currentTextSegment.addParagraph(paragraphSegment);
            if (nextTextParser != null) {
                nextTextParser.parse(paragraphSegment, paragraphString.trim());
            }
        }
    }
}
