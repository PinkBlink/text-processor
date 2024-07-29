package org.text.processor.action;

import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Text;
import org.text.processor.entity.TextSegment;

public class ParagraphParser extends Parser {
    @Override
    public void parse(TextSegment textSegment, String text) {
        Text currentTextSegment = (Text) textSegment;
        String[] paragraphs = text.split(TextConstants.LINE_BREAK_SEPARATOR);

        for (String paragraphString : paragraphs) {
            Paragraph paragraphSegment = new Paragraph();
            currentTextSegment.addParagraph(paragraphSegment);
            if (nextParser != null) {
                nextParser.parse(paragraphSegment, paragraphString);
            }
        }
    }
}
