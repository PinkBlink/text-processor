package org.text.processor.action.interpretator;

import java.util.Objects;

public class NotExpression implements Expression {
    private final Expression operand;

    public NotExpression(Expression operand) {
        this.operand = operand;
    }

    @Override
    public int interpret() {
        return ~operand.interpret();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotExpression that)) {
            return false;
        }
        return Objects.equals(operand, that.operand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand);
    }

    @Override
    public String toString() {
        return "NotExpression :( " + operand + " )";
    }
}