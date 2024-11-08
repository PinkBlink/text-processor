package org.text.processor.action.interpretator;

public interface Expression {
    int interpret();

    boolean equals(Object o);

    int hashCode();
}