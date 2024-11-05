package org.text.processor.entity;

import org.text.processor.constants.TextConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence extends TextSegment {
    private List<Word> wordList = new ArrayList<>();
    private final List<PunctuationMark> markList = new ArrayList<>();

    public void addWord(Word... words) {
        wordList.addAll(Arrays.asList(words));
    }

    public void addPunctuation(PunctuationMark... punctuationMarks) {
        markList.addAll(Arrays.asList(punctuationMarks));
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        int indexOfPunctuation = 0;
        for (int i = 0; i < wordList.size(); i++) {
            Word word = wordList.get(i);
            stringBuilder.append(word.getContent());
            if (indexOfPunctuation < markList.size() && i == markList.get(indexOfPunctuation).getIndexInSentence()) {
                PunctuationMark punctuationMark = markList.get(indexOfPunctuation);
                stringBuilder.append(punctuationMark.getContent());
                indexOfPunctuation++;
            }
            stringBuilder.append(TextConstants.SPACE_SEPARATOR);
        }
        return stringBuilder.toString().trim();
    }
}
