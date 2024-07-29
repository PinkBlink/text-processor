package org.text.processor.entity;

import org.text.processor.constants.TextConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paragraph extends TextSegment {
    private List<Sentence> sentenceList = new ArrayList<>();

    public void addSentence(Sentence... sentences) {
        sentenceList.addAll(Arrays.asList(sentences));
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TextConstants.TAB_SEPARATOR);
        for (Sentence sentence : sentenceList) {
            stringBuilder.append(sentence.getContent());
        }
        stringBuilder.append(TextConstants.LINE_BREAK_SEPARATOR);
        return stringBuilder.toString();
    }
}
