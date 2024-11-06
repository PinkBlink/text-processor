package org.text.processor.entity;

import org.text.processor.constants.TextConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence extends TextSegment {
    private List<Word> wordList = new ArrayList<>();
    private List<PunctuationMark> punctuationMarkList = new ArrayList<>();

    public void addWord(Word... words) {
        wordList.addAll(Arrays.asList(words));
    }

    public void addPunctuation(PunctuationMark... punctuationMarks) {
        this.punctuationMarkList.addAll(Arrays.asList(punctuationMarks));
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public List<PunctuationMark> getPunctuationMarkList() {
        return punctuationMarkList;
    }

    public void setPunctuationMarksList(List<PunctuationMark> list) {
        this.punctuationMarkList = list;
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        int indexOfPunctuation = 0;
        for (int i = 0; i < wordList.size(); i++) {
            Word word = wordList.get(i);
            stringBuilder.append(word.getContent());
            if (indexOfPunctuation < punctuationMarkList.size()
                    && i == punctuationMarkList.get(indexOfPunctuation).getIndexInSentence()) {

                PunctuationMark punctuationMark = punctuationMarkList.get(indexOfPunctuation);
                stringBuilder.append(punctuationMark.getContent());
                indexOfPunctuation++;
            }
            stringBuilder.append(TextConstants.SPACE_SEPARATOR);
        }
        return stringBuilder.toString().trim();
    }
}