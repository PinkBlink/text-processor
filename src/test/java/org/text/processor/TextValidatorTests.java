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

    @BeforeTest
    public void setUp() {
        validNumberString = "420";
        invalidNumberString1 = "420w";
        invalidNumberString2 = "";
        validExpressionString = "(7^5|1&2<<(2|5>>2&71))|1200";
        invalidExpression1 = "";
        invalidExpression2 = "20w";
        invalidExpression3 = "20*2";
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
}
