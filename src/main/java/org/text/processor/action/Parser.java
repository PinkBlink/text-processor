package org.text.processor.action;

import org.text.processor.entity.TextSegment;

public abstract class Parser {
    protected Parser nextParser;

    public void setNextParser(Parser parser) {
        this.nextParser = parser;
    }

    public abstract void parse(TextSegment textSegment, String text);
}
