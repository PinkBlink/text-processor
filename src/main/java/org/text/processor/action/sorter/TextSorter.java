package org.text.processor.action.sorter;

import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.Text;
import org.text.processor.entity.Word;

import java.util.Comparator;
import java.util.stream.Collectors;

/*
1 Отсортировать абзацы по количеству предложений. +
2 Отсортировать слова в предложении по длине.+
3 Отсортировать предложения в абзаце по количеству слов.+
4 Отсортировать лексемы в предложении по убыванию количества вхождений заданного символа,
а в случае равенства – по алфавиту.*/
public class TextSorter {

    public void sortParagraphsBySentencesCount(Text text) {
        text.setParagraphList(text.getParagraphList().stream()
                .sorted(Comparator.comparingInt(paragraph -> paragraph.getSentenceList().size()))
                .collect(Collectors.toList()));
    }

    public void sortSentencesInParagraphByWordsCount(Paragraph paragraph) {
        paragraph.setSentenceList(paragraph.getSentenceList().stream()
                .sorted(Comparator.comparingInt(sentence -> sentence.getWordList().size()))
                .toList());
    }

    public void sortWordsInSentenceByLength(Sentence sentence) {
        sentence.setWordList(sentence.getWordList().stream()
                .sorted(Comparator.comparingInt(word -> word.getContent().length()))
                .toList());
    }

    public void sortWordsInSentenceBySymbol(Sentence sentence, char symbol) {
        sentence.setWordList(sentence.getWordList().stream()
                .sorted(Comparator.comparing((Word w1) -> getAmountOfSymbolInWord(w1, symbol))
                        .thenComparing(Word::getContent))
                .toList());
    }

    private int getAmountOfSymbolInWord(Word word, char symbol) {
        return (int) word.getContent().chars().filter(s -> s == symbol).count();
    }
}
