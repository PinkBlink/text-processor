package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.action.parser.ParagraphTextParser;
import org.text.processor.action.parser.SentenceTextParser;
import org.text.processor.action.parser.TextParser;
import org.text.processor.action.parser.WordTextParser;
import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Text;
import org.text.processor.exception.NoFileException;
import org.text.processor.utils.DataFileReader;

import java.nio.file.Path;

public class TextParserTests {
    private String expectedText;
    private Text expected;

    @BeforeTest
    public void setUp() throws NoFileException {
        expectedText = DataFileReader.getTextFromData(Path.of("data/test.txt"));
    }

    @Test
    public void parserTest() {
        ParagraphTextParser paragraphTextParser = new ParagraphTextParser();
        SentenceTextParser sentenceTextParser = new SentenceTextParser();
        WordTextParser wordTextParser = new WordTextParser();
        paragraphTextParser.setNextParser(sentenceTextParser);
        sentenceTextParser.setNextParser(wordTextParser);
        Text actual = new Text();
        paragraphTextParser.parse(actual, expectedText);
        String actualString = actual.getContent();
        System.out.println("Длина ожидаемой строки: " + expectedText.length());
        System.out.println("Длина фактической строки: " + actualString.length());
        char[] actChars = actualString.toCharArray();
        char[] expChars = expectedText.toCharArray();
        for (int i = 0; i < Math.max(expChars.length, actChars.length); i++) {

            char expectedChar = (i < expChars.length) ? expChars[i] : ' ';
            char actualChar = (i < actChars.length) ? actChars[i] : ' ';

            if (expectedChar != actualChar) {
                System.out.println("Различие на индексе " + i + ": ожидается '" + expectedChar + "', фактически '" + actualChar + "'");
                System.out.println(expChars[i-1]);
            }
        }
        System.out.println(actualString.equals(expectedText));
        System.out.println(actual.getContent());
        System.out.println(expectedText);
        Assert.assertEquals(actualString, expectedText);
    }
}
