package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.utils.TextValidator;

public class TextValidatorTests {
    private String validNumberString;
    private String invalidNumberString1;
    private String invalidNumberString2;
    private String validExpressionString;
    private String invalidExpression1;
    private String invalidExpression2;
    private String invalidExpression3;
    private String validStringPunctuation1;
    private String validStringPunctuation2;
    private String validStringPunctuation3;
    private String invalidStringPunctuation;

    @BeforeTest
    public void setUp() {
        validNumberString = "420";
        invalidNumberString1 = "420w";
        invalidNumberString2 = "";
        validExpressionString = "(7^5|1&2<<(2|5>>2&71))|1200";
        invalidExpression1 = "";
        invalidExpression2 = "20w";
        invalidExpression3 = "20*2";
        validStringPunctuation1 = "word.";
        validStringPunctuation2 = "2|2?";
        validStringPunctuation3 = "Move!";
        invalidStringPunctuation = "Lol";
    }

    @Test
    public void isValidNumberPositive() {
        Assert.assertTrue(TextValidator.isNumber(validNumberString));
    }

    @Test
    public void isValidNumberNegative() {
        Assert.assertFalse(TextValidator.isNumber(invalidNumberString1));
        Assert.assertFalse(TextValidator.isNumber(invalidNumberString2));
    }

    @Test
    public void isValidExpressionPositive() {
        Assert.assertTrue(TextValidator.isValidExpression(validExpressionString));
    }

    @Test
    public void isValidExpressionNegative() {
        Assert.assertFalse(TextValidator.isValidExpression(invalidExpression1));
        Assert.assertFalse(TextValidator.isValidExpression(invalidExpression2));
        Assert.assertFalse(TextValidator.isValidExpression(invalidExpression3));
    }

    @Test
    public void hasPunctuationNegativeTest() {
        Assert.assertFalse(TextValidator.hasPunctuation(invalidStringPunctuation));
    }

    @Test
    public void hasPunctuation1PositiveTest() {
        Assert.assertTrue(TextValidator.hasPunctuation(validStringPunctuation1));
    }
    @Test
    public void hasPunctuation2PositiveTest() {
        Assert.assertTrue(TextValidator.hasPunctuation(validStringPunctuation2));
    }
    @Test
    public void hasPunctuation3PositiveTest() {
        Assert.assertTrue(TextValidator.hasPunctuation(validStringPunctuation3));
    }
}
