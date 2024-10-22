package org.text.processor;

import org.apache.logging.log4j.Logger;
import org.text.processor.action.ParagraphParser;
import org.text.processor.action.SentenceParser;
import org.text.processor.action.WordParser;
import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Text;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.text.processor.exception.NoFileException;
import org.text.processor.utils.DataFileReader;

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        try {
            String example = DataFileReader.getTextFromData();
            Text textSegment = new Text();
            paragraphParser.setNextParser(sentenceParser);
            sentenceParser.setNextParser(wordParser);
            paragraphParser.parse(textSegment, example);
            LOGGER.log(Level.INFO, TextConstants.LINE_BREAK_SEPARATOR + textSegment.getContent());

        } catch (NoFileException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }
}
