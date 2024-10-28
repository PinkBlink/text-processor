package org.text.processor.action.interpretator;

import org.text.processor.exception.IllegalExpressionException;

public enum BitwiseOperator {
    NOT(5) {
        @Override
        Expression getExpression(Expression left, Expression right) {
            throw new IllegalExpressionException("It is not possible to create NOT Expression with two arguments: " + right + ", " + left);
        }
    },
    LEFT_SHIFT(4) {
        @Override
        Expression getExpression(Expression left, Expression right) {
            return new LeftShiftExpression(left, right);
        }
    },
    RIGHT_SHIFT(4) {
        @Override
        Expression getExpression(Expression left, Expression right) {
            return new RightShiftExpression(left, right);
        }
    },
    AND(3) {
        @Override
        Expression getExpression(Expression left, Expression right) {
            return new AndExpression(left, right);
        }
    },
    XOR(2) {
        @Override
        Expression getExpression(Expression left, Expression right) {
            return new XorExpression(left, right);
        }
    },
    OR(1) {
        @Override
        Expression getExpression(Expression left, Expression right) {
            return new OrExpression(left, right);
        }
    };
    private final int priority;

    abstract Expression getExpression(Expression left, Expression right);

    public int getPriority() {
        return priority;
    }

    BitwiseOperator(int priority) {
        this.priority = priority;
    }
}
