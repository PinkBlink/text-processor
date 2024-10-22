package org.text.processor.action.interpretator;

public class NotExpression implements Expression {
    private final int number;

    public NotExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return ~number;
    }
}
