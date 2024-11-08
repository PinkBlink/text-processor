package org.text.processor.action.interpretator;

import org.text.processor.exception.IllegalExpressionException;

public enum BitwiseOperator {
    NOT(5),
    LEFT_SHIFT(4),
    RIGHT_SHIFT(4),
    AND(3),
    XOR(2),
    OR(1);
    private final int priority;

    public int getPriority() {
        return priority;
    }

    BitwiseOperator(int priority) {
        this.priority = priority;
    }

    public static BitwiseOperator getBitwiseOperator(char operator) {
        return switch (operator) {
            case '~' -> BitwiseOperator.NOT;
            case '>' -> BitwiseOperator.RIGHT_SHIFT;
            case '<' -> BitwiseOperator.LEFT_SHIFT;
            case '^' -> BitwiseOperator.XOR;
            case '|' -> BitwiseOperator.OR;
            case '&' -> BitwiseOperator.AND;
            default -> throw new IllegalExpressionException("Wrong bitwise operation: " + operator);
        };
    }
}