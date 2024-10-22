package org.text.processor.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.text.processor.constants.TextConstants;
import org.text.processor.exception.NoFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFileReader {
    private static final Logger LOGGER = LogManager.getLogger(DataFileReader.class);
    private static final String PATH_TO_DATA = "data/data.txt";

    public static String getTextFromData(Path path) throws NoFileException {
        String readingErrorMessage = "File at path " + path + " is not readable, or does not exist.";

        if (!Files.isReadable(path)) {
            LOGGER.log(Level.ERROR, readingErrorMessage);
            throw new NoFileException(readingErrorMessage);
        }
        try {
            return String.join(TextConstants.LINE_BREAK_SEPARATOR, Files.readAllLines(path));
        } catch (IOException e) {
            String detailedReadingErrorMessage = readingErrorMessage + ": " + e.getMessage();
            LOGGER.log(Level.ERROR, detailedReadingErrorMessage);
            throw new NoFileException(readingErrorMessage);
        }
    }

    public static String getTextFromData() throws NoFileException {
        return getTextFromData(Paths.get(PATH_TO_DATA));
    }
}