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

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    @Override
    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wordList.size(); i++) {
            Word word = wordList.get(i);
            if (i== wordList.size()-1){
                stringBuilder.append(word.getContent());
                break;
            }
            stringBuilder.append(word.getContent())
                    .append(TextConstants.SPACE_SEPARATOR);
        }
        return stringBuilder.toString();
    }
}
