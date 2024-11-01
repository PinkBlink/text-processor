package org.text.processor.entity;

import java.util.Objects;

public class Word extends TextSegment {
    private String content;

    public Word(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content.trim();
    }
}
