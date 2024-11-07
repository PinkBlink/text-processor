package org.text.processor.action.interpretator;

public interface Factory {
    Expression createBitwiseExpression(BitwiseOperator operator, Expression left, Expression right);

    Expression createNotExpression(Expression operand);

    Expression createNumberExpression(int operand);
}
