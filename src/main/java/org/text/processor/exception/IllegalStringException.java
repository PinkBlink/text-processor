package org.text.processor.exception;

public class IllegalStringException extends IllegalExpressionException {
    public IllegalStringException() {
        super();
    }

    public IllegalStringException(String message) {
        super(message);
    }

    public IllegalStringException(String message, Throwable cause) {
        super(message, cause);
    }
}