package org.text.processor.entity;

import org.text.processor.constants.TextConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence extends TextSegment {
    private List<Word> wordList = new ArrayList<>();
    private String finalPunctuation;

    private boolean isLastInParagraph;

    public boolean isLastInParagraph() {
        return isLastInParagraph;
    }

    public void setLastInParagraph(boolean lastInParagraph) {
        isLastInParagraph = lastInParagraph;
    }

    public void addWord(Word... words) {
        wordList.addAll(Arrays.asList(words));
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public void setFinalPunctuation(String finalPunctuation) {
        this.finalPunctuation = finalPunctuation;
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        int lastWordIndex = wordList.size() - 1;
        for (int i = 0; i < wordList.size(); i++) {
            Word word = wordList.get(i);
            stringBuilder.append(word.getContent());
            if (i == lastWordIndex) {
                break;
            }
            stringBuilder.append(TextConstants.SPACE_SEPARATOR);
        }
        stringBuilder.append(finalPunctuation);
        return stringBuilder.toString().trim();
    }
}
