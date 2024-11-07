package org.text.processor.action.interpretator;

import java.util.Objects;

public class XorExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public XorExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() ^ rightExpression.interpret();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XorExpression that)) {
            return false;
        }
        return Objects.equals(leftExpression, that.leftExpression) && Objects.equals(rightExpression, that.rightExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression);
    }

    @Override
    public String toString() {
        return "XorExpression :( leftExpression = " + leftExpression + ", rightExpression = " + rightExpression + " )";
    }
}