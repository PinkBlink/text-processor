package org.text.processor.action.interpretator;

import java.util.Objects;

public class LeftShiftExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public LeftShiftExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() << rightExpression.interpret();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeftShiftExpression that)) return false;
        return Objects.equals(leftExpression, that.leftExpression) && Objects.equals(rightExpression, that.rightExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression);
    }
}
