package org.text.processor.action.interpretator;

public class TestEntry {
    public static void main(String[] args) {
        Expression left = new NumberExpression(2);
        Expression right = new NumberExpression(4);
        System.out.println(new AndExpression(left, right).interpret());
    }
}
