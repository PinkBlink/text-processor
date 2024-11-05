package org.text.processor.entity;

public class PunctuationMark extends TextSegment {
    private final String content;
    private final int indexInSentence;

    public PunctuationMark(String content, int indexInSentence) {
        this.content = content;
        this.indexInSentence = indexInSentence;
    }

    public int getIndexInSentence(){
        return indexInSentence;
    }
    @Override
    public String getContent() {
        return content;
    }
}
