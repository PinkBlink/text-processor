package org.text.processor;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.entity.Text;
import org.text.processor.exception.NoFileException;
import org.text.processor.utils.DataFileReader;
import org.text.processor.utils.TextUtils;

import java.nio.file.Path;

public class TextParserTests {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private String expectedTextWithoutExpression;
    private String textWithExpression;
    private int expressionFromText;
    private String expectedTextWithExpression;
    private String textWithDifferentExpression;
    private int expressionFromDifferentText;
    private String expectedText;
    private String emptyText;
    private String textWithNoExpression;
    private String expectedTextWithoutCalculation;

    @BeforeTest
    public void setUp() throws NoFileException {
        expectedTextWithoutExpression = DataFileReader.getTextFromData(Constants.PATH_TO_TEST_TXT);
        textWithExpression = "    Just text with expression for example 3^12|213|(~12&5^2).";
        expressionFromText = 3 ^ 12 | 213 | (~12 & 5 ^ 2);
        expectedTextWithExpression = "    Just text with expression for example " + expressionFromText + ".";

        textWithDifferentExpression = "Calculate the result of 15&7|~2.";
        expressionFromDifferentText = 15 & 7 | ~2;
        expectedText = "    Calculate the result of " + expressionFromDifferentText + ".";

        emptyText = "";

        textWithNoExpression = "Just a simple text without calculations.";
        expectedTextWithoutCalculation = "    Just a simple text without calculations.";
    }

    @Test
    public void parserTest1() {
        Text actual = TextUtils.getParsedText(expectedTextWithoutExpression);
        String actualString = actual.getContent();
        logger.log(Level.INFO, "Expected: " + expectedTextWithoutExpression);
        logger.log(Level.INFO, "Actual: " + actualString);
        Assert.assertEquals(actualString, expectedTextWithoutExpression, actualString + " <-return");
    }

    @Test
    public void parserTest2() {
        try {
            String expected = DataFileReader.getTextFromData(Path.of("data/test_for_sorter.txt"));
            Text actual = TextUtils.getParsedText(expected);
            logger.log(Level.INFO, "Expected: " + expected);
            logger.log(Level.INFO, "Actual: " + actual.getContent());
            Assert.assertEquals(expected, actual.getContent());
        } catch (NoFileException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    @Test
    public void parserTest3() {
        Text actual = TextUtils.getParsedText(textWithDifferentExpression);
        String actualString = actual.getContent();
        logger.log(Level.INFO, "Expected: " + expectedText);
        logger.log(Level.INFO, "Actual: " + actualString);
        Assert.assertEquals(actualString, expectedText, actualString + " <-return");
    }

    @Test
    public void parserTest4() {
        Assert.assertThrows(IllegalArgumentException.class, () -> TextUtils.getParsedText(emptyText));
    }

    @Test
    public void parserTest5() {
        Text actual = TextUtils.getParsedText(textWithNoExpression);
        String actualString = actual.getContent();
        logger.log(Level.INFO, "Expected: " + expectedTextWithoutCalculation);
        logger.log(Level.INFO, "Actual: " + actualString);
        Assert.assertEquals(actualString, expectedTextWithoutCalculation, actualString + " <-return");
    }

    @Test
    public void parserTestWithExpression() {
        Text actual = TextUtils.getParsedText(textWithExpression);
        String actualString = actual.getContent();
        logger.log(Level.INFO, "Expected: " + expectedTextWithExpression);
        logger.log(Level.INFO, "Actual: " + actualString);
        Assert.assertEquals(actualString, expectedTextWithExpression, actualString + " <-return");
    }
}