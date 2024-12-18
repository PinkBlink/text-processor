package org.text.processor;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.text.processor.action.sorter.TextSorter;
import org.text.processor.entity.Paragraph;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.Text;
import org.text.processor.entity.Word;
import org.text.processor.exception.NoFileException;
import org.text.processor.utils.DataFileReader;
import org.text.processor.utils.TextUtils;


public class TextSorterTests {
    private final TextSorter textSorter = new TextSorter();
    private Text textFromData;

    @BeforeTest
    public void setUp() throws NoFileException {
        String textStringFromData = DataFileReader.getTextFromData(Constants.PATH_TO_TEXT_FOR_SORTER_TXT);
        textFromData = TextUtils.getParsedText(textStringFromData);
    }

    @Test
    public void testSortParagraphsBySentenceCount() {
        Text actual = textSorter.getSortedParagraphsBySentencesCount(textFromData);
        int paragraphsAmount = textFromData.getParagraphList().size();
        for (int i = 0; i < paragraphsAmount - 1; i++) {
            Paragraph currentParagraph = actual.getParagraphList().get(i);
            Paragraph nextParagraph = actual.getParagraphList().get(i);
            int currentParagraphLength = currentParagraph.getSentenceList().size();
            int nextParagraphLength = nextParagraph.getSentenceList().size();
            Assert.assertTrue(currentParagraphLength <= nextParagraphLength, currentParagraph.getContent());
        }
    }

    @Test
    public void testSortSentencesInParagraphByLength() {
        Paragraph paragraphFromData = textFromData.getParagraphList().get(1);
        Paragraph actual = textSorter.getSortedSentencesInParagraphByWordsCount(paragraphFromData);
        for (int i = 0; i < actual.getSentenceList().size() - 1; i++) {
            Sentence currentSentence = actual.getSentenceList().get(i);
            Sentence nextSentence = actual.getSentenceList().get(i);
            int currentSentenceLength = currentSentence.getWordList().size();
            int nextSentenceLength = nextSentence.getWordList().size();
            Assert.assertTrue(currentSentenceLength <= nextSentenceLength);
        }
    }

    @Test
    public void testSortWordsInSentenceByLength() {
        Sentence sentenceFromData = textFromData.getParagraphList().getFirst().getSentenceList().get(1);
        Sentence actual = textSorter.getSortedWordsInSentenceByLength(sentenceFromData);
        for (int i = 0; i < actual.getWordList().size() - 1; i++) {
            Word currentWord = actual.getWordList().get(i);
            Word nextWord = actual.getWordList().get(i + 1);
            int currentWordLength = currentWord.getContent().length();
            int nextWordLength = nextWord.getContent().length();
            Assert.assertTrue(currentWordLength <= nextWordLength);
        }
    }

    @Test
    public void testSortWordsInSentenceBySymbol1() {
        Sentence sentenceFromData = textFromData.getParagraphList().get(2).getSentenceList().get(1);
        char symbol = 'o';
        Sentence actual = textSorter.getSortedWordsInSentenceBySymbol(sentenceFromData, 'o');
        textSorter.getSortedWordsInSentenceBySymbol(actual, symbol);
        for (int i = 0; i < actual.getWordList().size() - 1; i++) {
            Word currentWord = actual.getWordList().get(i);
            Word nextWord = actual.getWordList().get(i);
            String message = "Current = " + currentWord.getContent() + " Next" + nextWord.getContent();
            Assert.assertTrue(isMoreSymbolsOrAlphabeticIfEquals(currentWord, nextWord, symbol), message);

        }
    }

    @Test
    public void testSortWordsInSentenceBySymbol2() {
        Sentence sentenceFromData = textFromData.getParagraphList().getFirst().getSentenceList().getFirst();
        char symbol = 'e';
        Sentence actual = textSorter.getSortedWordsInSentenceBySymbol(sentenceFromData, symbol);
        for (int i = 0; i < actual.getWordList().size() - 1; i++) {
            Word currentWord = actual.getWordList().get(i);
            Word nextWord = actual.getWordList().get(i + 1);
            String message = "Current = " + currentWord.getContent() + " Next = " + nextWord.getContent();
            Assert.assertTrue(isMoreSymbolsOrAlphabeticIfEquals(currentWord, nextWord, symbol), message);
        }
    }

    private boolean isMoreSymbolsOrAlphabeticIfEquals(Word currentWord, Word nextWord, char symbol) {
        int currentAmountOfSymbol = TextUtils.getAmountOfSymbolInWord(currentWord, symbol);
        int nextAmountOfSymbol = TextUtils.getAmountOfSymbolInWord(nextWord, symbol);
        char currentFirstChar = currentWord.getContent().toLowerCase().charAt(0);
        char nextFirstChar = currentWord.getContent().toLowerCase().charAt(0);
        boolean symbolAmountCondition = currentAmountOfSymbol > nextAmountOfSymbol;
        boolean alphabeticConditionWhenEqualsAmount = currentAmountOfSymbol == nextAmountOfSymbol
                && currentFirstChar <= nextFirstChar;
        return symbolAmountCondition || alphabeticConditionWhenEqualsAmount;
    }
}
