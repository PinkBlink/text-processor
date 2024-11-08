package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.action.interpretator.Expression;
import org.text.processor.action.interpretator.ExpressionEvaluator;
import org.text.processor.action.interpretator.ExpressionParser;
import org.text.processor.exception.IllegalExpressionException;

public class ExpressionEvaluatorTests {
    private ExpressionParser expressionParser = new ExpressionParser();
    private String expressionString1;
    private String expressionString2;
    private String expressionString3;
    private String expressionString4;
    private String expressionString5;
    private String expressionString6;
    private String expressionString7;
    private String expressionString8;
    private String expressionString9;
    private int expectedExpression1;
    private int expectedExpression2;
    private int expectedExpression3;
    private int expectedExpression4;
    private int expectedExpression5;
    private int expectedExpression6;
    private int expectedExpression7;
    private int expectedExpression8;
    private int expectedExpression9;

    private String invalidExpression;

    @BeforeTest
    public void setUp() {
        expressionParser = new ExpressionParser();
        expressionString1 = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
        expressionString2 = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2)|78)";
        expressionString3 = "~6&9|(3&4)";
        expressionString4 = "(7^5|1&2<<(2|5>>2&71))|1200";
        expressionString5 = "13<<2";
        expressionString6 = "3>>5";
        expressionString7 = "(3|(2&1<<1))|4";
        expressionString8 = "(2&3)|(4^1)";
        expressionString9 = "~3&(2|1)";
        invalidExpression = "2^3^12|";
        expectedExpression1 = 5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1);
        expectedExpression2 = (~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) & 2) | 10 & 2) | 78);  //
        expectedExpression3 = ~6 & 9 | (3 & 4);
        expectedExpression4 = (7 ^ 5 | 1 & 2 << (2 | 5 >> 2 & 71)) | 1200;
        expectedExpression5 = 13 << 2;
        expectedExpression6 = 3 >> 5;
        expectedExpression7 = (3 | (2 & 1 << 1)) | 4;
        expectedExpression8 = (2 & 3) | (4 ^ 1);
        expectedExpression9 = ~3 & (2 | 1);
    }

    @Test
    public void expressionInterpretTest1() {
        int actual = getExpressionResult(expressionString1);
        Assert.assertEquals(actual, expectedExpression1);
    }

    @Test
    public void expressionInterpretTest2() {
        int actual = getExpressionResult(expressionString2);
        Assert.assertEquals(actual, expectedExpression2);
    }

    @Test
    public void expressionInterpretTest3() {
        int actual = getExpressionResult(expressionString3);
        Assert.assertEquals(actual, expectedExpression3);
    }

    @Test
    public void expressionInterpretTest4() {
        int actual = getExpressionResult(expressionString4);
        Assert.assertEquals(actual, expectedExpression4);
    }

    @Test
    public void expressionInterpretTest5() {
        int actual = getExpressionResult(expressionString5);
        Assert.assertEquals(actual, expectedExpression5);
    }

    @Test
    public void expressionInterpretTest6() {
        int actual = getExpressionResult(expressionString6);
        Assert.assertEquals(actual, expectedExpression6);
    }

    @Test
    public void expressionInterpretTest7() {
        int actual = getExpressionResult(expressionString7);
        Assert.assertEquals(actual, expectedExpression7);
    }

    @Test
    public void expressionInterpretTest8() {
        int actual = getExpressionResult(expressionString8);
        Assert.assertEquals(actual, expectedExpression8);
    }

    @Test
    public void expressionInterpretTest9() {
        int actual = getExpressionResult(expressionString9);
        Assert.assertEquals(actual, expectedExpression9);
    }

    @Test
    public void expressionInvalidExpression(){
        Assert.assertThrows(IllegalExpressionException.class,()-> getExpressionResult(invalidExpression));
    }

    private int getExpressionResult(String expression) {
        expressionParser.parse(expression);
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(expressionParser);
        Expression result = expressionEvaluator.getCombinedExpression();
        return result.interpret();
    }
}
