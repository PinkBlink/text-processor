package org.text.processor.entity;

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
        for (Paragraph paragraph : paragraphList) {
            stringBuilder.append(paragraph.getContent());
        }
        return stringBuilder.toString();
    }
}
