package org.text.processor.action.interpretator;

public class AndExpression implements Expression{
    private final Expression leftExpression;
    private final Expression rightExpression;

    public AndExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() & rightExpression.interpret();
    }
}
