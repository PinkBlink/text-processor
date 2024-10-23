package org.text.processor.action.interpretator;

public enum BitwiseOperatorPriority {
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

    BitwiseOperatorPriority(int priority) {
        this.priority = priority;
    }
}
