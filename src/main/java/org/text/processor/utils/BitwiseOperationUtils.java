package org.text.processor.utils;

import org.text.processor.action.interpretator.BitwiseOperator;
import org.text.processor.constants.TextConstants;
import org.text.processor.exception.IllegalExpressionException;

import java.util.regex.Pattern;

public class BitwiseOperationUtils {

    public static BitwiseOperator getBitwiseOperator(char operator) {
        return switch (operator) {
            case '~' -> BitwiseOperator.NOT;
            case '>' -> BitwiseOperator.RIGHT_SHIFT;
            case '<' -> BitwiseOperator.LEFT_SHIFT;
            case '^' -> BitwiseOperator.XOR;
            case '|' -> BitwiseOperator.OR;
            case '&' -> BitwiseOperator.AND;
            default -> throw new IllegalExpressionException("Wrong bitwise operation: " + operator);
        };
    }

    public static void main(String[] args) {
        String testExpression1 = "13<<2";
        String testExpression2 = "3>>5";
        String testExpression3 = "~6&9|(3&4)";
        String testExpression4 = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
        String testExpression5 = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
        String testExpression6 = "(7^5|1&2<<(2|5>>2&71))|1200";
        System.out.println(5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1));
    }
}
