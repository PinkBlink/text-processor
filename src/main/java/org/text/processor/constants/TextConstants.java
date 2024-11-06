package org.text.processor.constants;

public class TextConstants {
    public static final String TAB_SEPARATOR = "    ";
    public static final String LINE_BREAK_SEPARATOR = "\n";
    public static final String SPACE_SEPARATOR = " ";
    public static final String SENTENCE_SEPARATOR_REGEX = "(?<=[.!?])\\s+";
    public static final String EXPRESSION_REGEX = "^([0-9]*[()~&><^|]*){2,}$";
    public static final String PUNCTUATION_REGEX = "[a-zA-Z0-9&^|><~()]*[.?,!]";
    public static final char LEFT_BRACKET = '(';
    public static final char RIGHT_BRACKET = ')';
    public static final int MAX_BITWISE_OPERATOR_PRIORITY = 5;
    public static final String PATH_TO_DATA = "data/data.txt";
    public static final int STEP = 1;
}