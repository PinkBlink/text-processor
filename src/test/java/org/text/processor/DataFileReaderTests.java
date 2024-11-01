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
        String validPathString = "data/test.txt";
        validPath = Path.of(validPathString);
        String invalidPathString = "data/";
        invalidPath = Path.of(invalidPathString);
        textFromData = "File for test.";
    }

    @Test
    public void getTextFromDataPositive() throws NoFileException {
        String actual = DataFileReader.getTextFromData(validPath);
        Assert.assertEquals(actual, textFromData);
    }

    @Test
    public void getTextFromDataNegative() {
        Assert.assertThrows(NoFileException.class, () -> DataFileReader.getTextFromData(invalidPath));
    }
}
