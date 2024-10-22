package org.text.processor.action.interpretator;

public class OrExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public OrExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() ^ rightExpression.interpret();
    }
}
