package org.text.processor.action.parser;

import org.text.processor.entity.TextSegment;

public abstract class TextParser {
    protected TextParser nextTextParser;

    public void setNextParser(TextParser textParser) {
        this.nextTextParser = textParser;
    }

    public abstract void parse(TextSegment textSegment, String text);
}