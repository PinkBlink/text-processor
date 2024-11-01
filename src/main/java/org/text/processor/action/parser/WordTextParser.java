package org.text.processor.action.parser;

import org.text.processor.action.interpretator.Expression;
import org.text.processor.action.interpretator.ExpressionEvaluator;
import org.text.processor.action.interpretator.ExpressionParser;
import org.text.processor.constants.TextConstants;
import org.text.processor.entity.Sentence;
import org.text.processor.entity.TextSegment;
import org.text.processor.entity.Word;
import org.text.processor.utils.TextValidator;

public class WordTextParser extends TextParser {
    @Override
    public void parse(TextSegment textSegment, String text) {
        Sentence sentenceSegment = (Sentence) textSegment;
        String[] words = text.split(TextConstants.SPACE_SEPARATOR);

        for (String wordString : words) {
            if(TextValidator.isValidExpression(wordString)){
                ExpressionParser expressionParser = new ExpressionParser();
                expressionParser.parse(wordString);
                ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(expressionParser);
                Expression expression = expressionEvaluator.getCombinedExpression();
                wordString = String.valueOf(expression.interpret());
            }
            if(wordString.isEmpty()){
                continue;
            }
            Word wordSegment = new Word(wordString.trim());
            sentenceSegment.addWord(wordSegment);
        }
    }
}
