package org.text.processor;

import org.apache.logging.log4j.Logger;
import org.text.processor.action.interpretator.Expression;
import org.text.processor.action.interpretator.ExpressionParser;
import org.text.processor.action.parser.ParagraphParser;
import org.text.processor.action.parser.SentenceParser;
import org.text.processor.action.parser.WordParser;
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

        ExpressionParser expressionParser = new ExpressionParser();
        String expressionString1 = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
        String expressionString2 = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2)|78)";//
        String expressionString3 = "~6&9|(3&4)";
        String expressionString4 = "(7^5|1&2<<(2|5>>2&71))|1200";
        String expressionString5 = "13<<2";
        String expressionString6 = "3>>5";
        String expressionString7 = "(3|(2&1<<1))|4";
        String expressionString8 = "(2&3)|(4^1)";
        String expressionString9 = "~3&(2|1)";
        int expression1 = 5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1);
        int expression2 = (~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) & 2) | 10 & 2) | 78);  //
        int expression3 = ~6 & 9 | (3 & 4);
        int expression4 = (7 ^ 5 | 1 & 2 << (2 | 5 >> 2 & 71)) | 1200;
        int expression5 = 13 << 2;
        int expression6 = 3 >> 5;
        int expression7 = (3 | (2 & 1 << 1)) | 4;
        int expression8 = (2 & 3) | (4 ^ 1);
        int expression9 = ~3 & (2 | 1);
        testParser(expressionParser, expressionString1, expression1);
        testParser(expressionParser, expressionString2, expression2);
        testParser(expressionParser, expressionString3, expression3);
        testParser(expressionParser, expressionString4, expression4);
        testParser(expressionParser, expressionString5, expression5);
        testParser(expressionParser, expressionString6, expression6);
        testParser(expressionParser, expressionString7, expression7);
        testParser(expressionParser, expressionString8, expression8);
        testParser(expressionParser, expressionString9, expression9);
    }

    static void testParser(ExpressionParser parser, String expressionString, int expressionInt) {
        parser.parse(expressionString);
        Expression expression = parser.getCombinedExpression();
        System.out.println("-------------------");
        System.out.println("should be:");
        System.out.println(expressionInt);
        System.out.println("output:");
        System.out.println(expression.interpret());
        System.out.println("--------------");
    }
}

