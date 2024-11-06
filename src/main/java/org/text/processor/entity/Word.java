package org.text.processor.entity;

public class Word extends TextSegment {
    private String content;

    public Word(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content.trim();
    }
}