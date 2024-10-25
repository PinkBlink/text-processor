package org.text.processor.action.interpretator;

public class NotExpression implements Expression {
    private final Expression operand;

    public NotExpression(Expression operand) {
        this.operand = operand;
    }

    @Override
    public int interpret() {
        return ~operand.interpret();
    }
}
