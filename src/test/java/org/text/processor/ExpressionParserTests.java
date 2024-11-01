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
    private List<BitwiseOperator> expectedOperatorsExpression1;
    private List<Expression> expectedNumbersExpression1;

    private String expression2;
    private List<BitwiseOperator> expectedOperatorsExpression2;
    private List<Expression> expectedNumbersExpression2;

    @BeforeTest
    public void setUp() {
        parser = new ExpressionParser();
        expression1 = "11^22|33";

        expectedNumbersExpression1 = Arrays.asList(
                new NumberExpression(11)
                , new NumberExpression(22)
                , new NumberExpression(33));
        expectedOperatorsExpression1 = Arrays.asList(BitwiseOperator.XOR, BitwiseOperator.OR);

        expression2 = "125^(22|~33123)";
        expectedNumbersExpression2 = Arrays.asList(
                new NumberExpression(125)
                , new OrExpression(
                        new NumberExpression(22)
                        , new NotExpression(new NumberExpression(33123))));
        expectedOperatorsExpression2 = List.of(BitwiseOperator.XOR);
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
}
