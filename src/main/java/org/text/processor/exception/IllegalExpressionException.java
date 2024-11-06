package org.text.processor.exception;

public class IllegalExpressionException extends IllegalArgumentException {
    public IllegalExpressionException() {
        super();
    }

    public IllegalExpressionException(String message) {
        super(message);
    }

    public IllegalExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}