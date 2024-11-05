package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.exception.NoFileException;
import org.text.processor.utils.DataFileReader;

import java.nio.file.Path;

public class DataFileReaderTests {
    private Path validPath;
    private Path invalidPath;
    private String textFromData;

    @BeforeTest
    public void setUp() {
        validPath = Constants.PATH_TO_TEST_TXT;
        String invalidPathString = "wrong_path>,";
        invalidPath = Path.of(invalidPathString);
        textFromData = "    File for test and only for that.\n" +
                "    If you want to change something here feel free, but also change variable in test! Just test.";
    }

    @Test
    public void testGetTextFromDataPositive() throws NoFileException {
        String actual = DataFileReader.getTextFromData(validPath);
        System.out.println(actual);
        System.out.println(textFromData);
        Assert.assertEquals(actual, textFromData);
    }

    @Test
    public void testGetTextFromDataNegative() {
        Assert.assertThrows(NoFileException.class, () -> DataFileReader.getTextFromData(invalidPath));
    }
}
