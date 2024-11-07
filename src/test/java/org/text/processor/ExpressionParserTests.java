package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.action.interpretator.*;

import java.util.Arrays;
import java.util.List;

public class ExpressionParserTests {
    private ExpressionParser parser;

    private String expression1;
    private String expression2;
    private String expression3;
    private String expression4;
    private String expression5;
    private String expression6;

    private List<BitwiseOperator> expectedOperatorsExpression1;
    private List<Expression> expectedNumbersExpression1;
    private List<BitwiseOperator> expectedOperatorsExpression2;
    private List<Expression> expectedNumbersExpression2;
    private List<BitwiseOperator> expectedOperatorsExpression3;
    private List<Expression> expectedNumbersExpression3;
    private List<BitwiseOperator> expectedOperatorsExpression4;
    private List<Expression> expectedNumbersExpression4;
    private List<BitwiseOperator> expectedOperatorsExpression5;
    private List<Expression> expectedNumbersExpression5;
    private List<BitwiseOperator> expectedOperatorsExpression6;
    private List<Expression> expectedNumbersExpression6;

    @BeforeTest
    public void setUp() {
        parser = new ExpressionParser();

        expression1 = "11^22|33";
        expression2 = "125^(22|~33123)";
        expression3 = "45&~12|6";
        expression4 = "10|5&2^3";
        expression5 = "~7&1|9^3";
        expression6 = "8^4|2&~3";

        expectedNumbersExpression1 = Arrays.asList(
                new NumberExpression(11),
                new NumberExpression(22),
                new NumberExpression(33));
        expectedOperatorsExpression1 = Arrays.asList(BitwiseOperator.XOR, BitwiseOperator.OR);

        expectedNumbersExpression2 = Arrays.asList(
                new NumberExpression(125),
                new OrExpression(
                        new NumberExpression(22),
                        new NotExpression(new NumberExpression(33123))));
        expectedOperatorsExpression2 = List.of(BitwiseOperator.XOR);

        expectedNumbersExpression3 = Arrays.asList(
                new NumberExpression(45),
                new NotExpression(new NumberExpression(12)),
                new NumberExpression(6));
        expectedOperatorsExpression3 = Arrays.asList(BitwiseOperator.AND, BitwiseOperator.OR);

        expectedNumbersExpression4 = Arrays.asList(
                new NumberExpression(10),
                new NumberExpression(5),
                new NumberExpression(2),
                new NumberExpression(3));
        expectedOperatorsExpression4 = Arrays.asList(BitwiseOperator.OR, BitwiseOperator.AND, BitwiseOperator.XOR);

        expectedNumbersExpression5 = Arrays.asList(
                new NotExpression(new NumberExpression(7)),
                new NumberExpression(1),
                new NumberExpression(9),
                new NumberExpression(3));
        expectedOperatorsExpression5 = Arrays.asList(BitwiseOperator.AND, BitwiseOperator.OR, BitwiseOperator.XOR);

        expectedNumbersExpression6 = Arrays.asList(
                new NumberExpression(8),
                new NumberExpression(4),
                new NumberExpression(2),
                new NotExpression(new NumberExpression(3)));
        expectedOperatorsExpression6 = Arrays.asList(BitwiseOperator.XOR, BitwiseOperator.OR, BitwiseOperator.AND);
    }

    @Test
    public void parseExpression1Test() {
        parser.parse(expression1);
        List<Expression> actualNumbersExpression = parser.getNumberExpressionList();
        List<BitwiseOperator> actualOperatorsExpression = parser.getOperationExpressionsList();
        Assert.assertEquals(actualOperatorsExpression, expectedOperatorsExpression1);
        Assert.assertEquals(actualNumbersExpression, expectedNumbersExpression1);
    }

    @Test
    public void parseExpression2Test() {
        parser.parse(expression2);
        List<Expression> actualNumbersExpression = parser.getNumberExpressionList();
        List<BitwiseOperator> actualOperatorsExpression = parser.getOperationExpressionsList();
        Assert.assertEquals(actualOperatorsExpression, expectedOperatorsExpression2);
        Assert.assertEquals(actualNumbersExpression, expectedNumbersExpression2);
    }

    @Test
    public void parseExpression3Test() {
        parser.parse(expression3);
        List<Expression> actualNumbersExpression = parser.getNumberExpressionList();
        List<BitwiseOperator> actualOperatorsExpression = parser.getOperationExpressionsList();
        Assert.assertEquals(actualOperatorsExpression, expectedOperatorsExpression3);
        Assert.assertEquals(actualNumbersExpression, expectedNumbersExpression3);
    }

    @Test
    public void parseExpression4Test() {
        parser.parse(expression4);
        List<Expression> actualNumbersExpression = parser.getNumberExpressionList();
        List<BitwiseOperator> actualOperatorsExpression = parser.getOperationExpressionsList();
        Assert.assertEquals(actualOperatorsExpression, expectedOperatorsExpression4);
        Assert.assertEquals(actualNumbersExpression, expectedNumbersExpression4);
    }

    @Test
    public void parseExpression5Test() {
        parser.parse(expression5);
        List<Expression> actualNumbersExpression = parser.getNumberExpressionList();
        List<BitwiseOperator> actualOperatorsExpression = parser.getOperationExpressionsList();
        Assert.assertEquals(actualOperatorsExpression, expectedOperatorsExpression5);
        Assert.assertEquals(actualNumbersExpression, expectedNumbersExpression5);
    }

    @Test
    public void parseExpression6Test() {
        parser.parse(expression6);
        List<Expression> actualNumbersExpression = parser.getNumberExpressionList();
        List<BitwiseOperator> actualOperatorsExpression = parser.getOperationExpressionsList();
        Assert.assertEquals(actualOperatorsExpression, expectedOperatorsExpression6);
        Assert.assertEquals(actualNumbersExpression, expectedNumbersExpression6);
    }
}