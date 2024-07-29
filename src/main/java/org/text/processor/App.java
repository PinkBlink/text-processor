package org.text.processor;

import org.text.processor.action.ParagraphParser;
import org.text.processor.action.SentenceParser;
import org.text.processor.action.WordParser;
import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.Text;
import org.text.processor.entity.Word;

public class App {
    public static void main(String[] args) {
        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        String example = "Pomfaw powafm papfm pamf pfmfme mpmaf.\nDadawok, awdomwd omfeo o o o ofoemafo!";
        Text textSegment = new Text();
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(wordParser);
        paragraphParser.parse(textSegment,example);
        System.out.println(textSegment.getContent());
    }
}
