package org.text.processor.action.parser;

import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Text;
import org.text.processor.entity.TextSegment;

public class ParagraphTextParser extends TextParser {
    @Override
    public void parse(TextSegment textSegment, String text) {
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
