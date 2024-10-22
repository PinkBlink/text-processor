package org.text.processor.utils;

import java.nio.file.Files;
import java.nio.file.Path;

public class TextValidator {
    public boolean isByteExpression(String string) {
        return string.contains(">>");
    }

    public boolean isDigit(String string) {
        // need to implement
        return false;
    }

    public boolean isNumber(String string) {
        // need to implement
        return false;
    }
}
