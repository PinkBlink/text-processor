package org.text.processor;

import org.apache.logging.log4j.Logger;
import org.text.processor.action.ParagraphParser;
import org.text.processor.action.SentenceParser;
import org.text.processor.action.WordParser;
import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.Text;
import org.text.processor.entity.Word;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger l = LogManager.getLogger(App.class);
    public static void main(String[] args) {
        l.log(Level.WARN,"that's work");
        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        String example = "Pomfaw powafm papfm pamf pfmfme mpmaf.\nDadawok, awdomwd omfeo o o o ofoemafo!";
        Text textSegment = new Text();
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(wordParser);
        paragraphParser.parse(textSegment, example);
        System.out.println(textSegment.getContent());
    }
}
