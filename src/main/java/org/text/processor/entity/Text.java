package org.text.processor.entity;

import org.text.processor.constants.TextConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Text extends TextSegment {
    private List<Paragraph> paragraphList = new ArrayList<>();

    public void addParagraph(Paragraph... paragraphs) {
        paragraphList.addAll(Arrays.asList(paragraphs));

    }

    public List<Paragraph> getParagraphList() {
        return paragraphList;
    }

    public void setParagraphList(List<Paragraph> paragraphList) {
        this.paragraphList = paragraphList;
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        int lastParagraphIndex = paragraphList.size() - TextConstants.STEP;
        for (int i = 0; i < paragraphList.size(); i++) {
            Paragraph paragraph = paragraphList.get(i);
            stringBuilder.append(paragraph.getContent());
            if (i == lastParagraphIndex) {
                break;
            }
            stringBuilder.append(TextConstants.LINE_BREAK_SEPARATOR);
        }
        return stringBuilder.toString();
    }
}
