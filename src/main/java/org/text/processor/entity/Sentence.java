package org.text.processor.entity;

import org.text.processor.constants.TextConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence extends TextSegment {
    private List<Word> wordList = new ArrayList<>();

    public void addWord(Word... word) {
        wordList.addAll(Arrays.asList(word));
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Word word : wordList) {
            stringBuilder.append(word.getContent())
                .append(TextConstants.SPACE_SEPARATOR);
        }
        return stringBuilder.toString();
    }
}
