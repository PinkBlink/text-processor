package org.text.processor.action.sorter;

import org.text.processor.entity.*;
import org.text.processor.utils.TextUtils;

import java.util.Comparator;
import java.util.stream.Collectors;

public class TextSorter {
    public Text getSortedParagraphsBySentencesCount(Text text) {
        Text result = new Text();
        result.setParagraphList(text.getParagraphList());
        result.setParagraphList(result.getParagraphList().stream()
                .sorted(Comparator.comparingInt(paragraph -> paragraph.getSentenceList().size()))
                .collect(Collectors.toList()));
        return result;
    }

    public Paragraph getSortedSentencesInParagraphByWordsCount(Paragraph paragraph) {
        Paragraph result = new Paragraph();
        result.setSentenceList(paragraph.getSentenceList());

        result.setSentenceList(result.getSentenceList().stream()
                .sorted(Comparator.comparingInt(sentence -> sentence.getWordList().size()))
                .toList());
        return result;
    }

    public Sentence getSortedWordsInSentenceByLength(Sentence sentence) {
        Sentence result = new Sentence();
        result.setWordList(sentence.getWordList());
        result.setPunctuationMarksList(sentence.getPunctuationMarkList());
        result.setWordList(result.getWordList().stream()
                .sorted(Comparator.comparingInt(word -> word.getContent().length()))
                .toList());
        return result;
    }

    public Sentence getSortedWordsInSentenceBySymbol(Sentence sentence, char symbol) {
        Sentence result = new Sentence();
        result.setWordList(sentence.getWordList());
        result.setPunctuationMarksList(sentence.getPunctuationMarkList());
        result.setWordList(result.getWordList().stream()
                .sorted(Comparator
                        .comparing((Word w) -> TextUtils.getAmountOfSymbolInWord(w, symbol), Comparator.reverseOrder())
                        .thenComparing(w -> w.getContent().toLowerCase()))
                .toList());
        return result;
    }
}