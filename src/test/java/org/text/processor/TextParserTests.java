package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.action.parser.ParagraphTextParser;
import org.text.processor.action.parser.SentenceTextParser;
import org.text.processor.action.parser.WordTextParser;
import org.text.processor.entity.Text;
import org.text.processor.exception.NoFileException;
import org.text.processor.utils.DataFileReader;

import java.nio.file.Path;

public class TextParserTests {
    private String expectedText1;

    private String textWithExpression;
    int expressionFromText;

    private String expectedTextWithResolvedExpression;

    @BeforeTest
    public void setUp() throws NoFileException {
        expectedText1 = DataFileReader.getTextFromData(Path.of("data/test.txt"));
        textWithExpression = "    Just text with expression for example 3^12|213|(~12&5^2).";
        expressionFromText = 3 ^ 12 | 213 | (~12 & 5 ^ 2);
        expectedTextWithResolvedExpression = "    Just text with expression for example " + expressionFromText + ".";

    }

    @Test
    public void parserTest1() {
        Text actual = getTextFromChain(expectedText1);
        String actualString = actual.getContent();
        Assert.assertEquals(actualString, expectedText1, actualString + " <-return");
    }

    @Test
    public void parserTestWithExpression() {
        Text actual = getTextFromChain(textWithExpression);
        String actualString = actual.getContent();
        Assert.assertEquals(actualString, expectedTextWithResolvedExpression, actualString + " <-return");
    }

    private Text getTextFromChain(String text) {
        ParagraphTextParser paragraphTextParser = new ParagraphTextParser();
        SentenceTextParser sentenceTextParser = new SentenceTextParser();
        WordTextParser wordTextParser = new WordTextParser();
        paragraphTextParser.setNextParser(sentenceTextParser);
        sentenceTextParser.setNextParser(wordTextParser);
        Text actual = new Text();
        paragraphTextParser.parse(actual, text);
        return actual;
    }
}
