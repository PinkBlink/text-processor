package org.text.processor.action.interpretator;

import org.text.processor.exception.IllegalExpressionException;

public class ExpressionFactoryImpl implements ExpressionFactory {
    @Override
    public Expression createBitwiseExpression(BitwiseOperator operator, Expression left, Expression right) {
        return switch (operator) {
            case XOR -> new XorExpression(left, right);
            case OR -> new OrExpression(left, right);
            case LEFT_SHIFT -> new LeftShiftExpression(left, right);
            case RIGHT_SHIFT -> new RightShiftExpression(left, right);
            case AND -> new AndExpression(left, right);
            case NOT -> throw new IllegalExpressionException("Trying to create NOT expression with two arguments;");
        };
    }

    @Override
    public Expression createNotExpression(Expression operand) {
        return new NotExpression(operand);
    }

    @Override
    public Expression createNumberExpression(int operand) {
        return new NumberExpression(operand);
    }
}