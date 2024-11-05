package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.entity.Text;
import org.text.processor.exception.NoFileException;
import org.text.processor.utils.DataFileReader;
import org.text.processor.utils.TextUtils;

import java.nio.file.Path;

public class TextParserTests {
    private String expectedTextWithoutExpression;

    private String textWithExpression;
    int expressionFromText;

    private String expectedTextWithExpression;

    @BeforeTest
    public void setUp() throws NoFileException {
        expectedTextWithoutExpression = DataFileReader.getTextFromData(Path.of("data/test.txt"));
        textWithExpression = "    Just text with expression for example 3^12|213|(~12&5^2).";
        expressionFromText = 3 ^ 12 | 213 | (~12 & 5 ^ 2);
        expectedTextWithExpression = "    Just text with expression for example " + expressionFromText + ".";
    }

    @Test
    public void parserTest1() {
        Text actual = TextUtils.getParsedText(expectedTextWithoutExpression);
        String actualString = actual.getContent();
        Assert.assertEquals(actualString, expectedTextWithoutExpression, actualString + " <-return");
    }

    @Test
    public void parserTestWithExpression() {
        Text actual = TextUtils.getParsedText(textWithExpression);
        String actualString = actual.getContent();
        Assert.assertEquals(actualString, expectedTextWithExpression, actualString + " <-return");
    }
}
