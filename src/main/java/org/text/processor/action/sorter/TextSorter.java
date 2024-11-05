package org.text.processor.action.sorter;

import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.Text;
import org.text.processor.entity.Word;

import java.util.Comparator;
import java.util.stream.Collectors;

public class TextSorter {

    public void getSortedParagraphsBySentencesCount(Text text) {
        text.setParagraphList(text.getParagraphList().stream()
                .sorted(Comparator.comparingInt(paragraph -> paragraph.getSentenceList().size()))
                .collect(Collectors.toList()));
    }

    public void getSortedSentencesInParagraphByWordsCount(Paragraph paragraph) {
        paragraph.setSentenceList(paragraph.getSentenceList().stream()
                .sorted(Comparator.comparingInt(sentence -> sentence.getWordList().size()))
                .toList());
    }

    public void getSortedWordsInSentenceByLength(Sentence sentence) {
        sentence.setWordList(sentence.getWordList().stream()
                .sorted(Comparator.comparingInt(word -> word.getContent().length()))
                .toList());
    }

    public void getSortedWordsInSentenceBySymbol(Sentence sentence, char symbol) {
        sentence.setWordList(sentence.getWordList().stream()
                .sorted(Comparator
                        .comparing((Word w) -> getAmountOfSymbolInWord(w, symbol), Comparator.reverseOrder())
                        .thenComparing(w -> w.getContent().toLowerCase()))
                .toList());
    }

    private int getAmountOfSymbolInWord(Word word, char symbol) {
        return (int) word.getContent().chars().filter(s -> s == symbol).count();
    }
}
