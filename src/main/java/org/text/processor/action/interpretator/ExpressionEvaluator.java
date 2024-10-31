package org.text.processor.action.interpretator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.text.processor.constants.TextConstants;
import org.text.processor.exception.IllegalExpressionException;

import java.util.List;
import java.util.stream.IntStream;

public class ExpressionEvaluator {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private List<BitwiseOperator> operatorList;
    private List<Expression> expressionsList;

    public ExpressionEvaluator(ExpressionParser parser) {
        this.operatorList = parser.getOperationExpressionsList();
        this.expressionsList = parser.getNumberExpressionList();
    }

    public Expression getCombinedExpression() {
        mergeAllExpressions();
        if (expressionsList.size() == 1) {
            return expressionsList.getFirst();
        } else {
            logger.log(Level.ERROR, this.expressionsList + " <-- operation Expressions list");
            logger.log(Level.ERROR, this.expressionsList + "<-- Numbers list");
            throw new IllegalExpressionException("Error in combining expressions, left :" + expressionsList.size());
        }
    }

    private void mergeAllExpressions() {
        for (int i = TextConstants.MAX_BITWISE_OPERATOR_PRIORITY; i > 0; i--) {
            int indexOfOperator = getFirstIndexOfOperationInPriority(i);
            while (indexOfOperator != -1) {
                mergeExceptionWithOperator(indexOfOperator);
                indexOfOperator = getFirstIndexOfOperationInPriority(i);
            }
        }
    }

    private void mergeExceptionWithOperator(int operatorIndex) {
        BitwiseOperator operator = operatorList.get(operatorIndex);
        Expression firstOperand = expressionsList.get(operatorIndex);
        Expression secondOperand = expressionsList.get(operatorIndex + 1);
        Expression resultExpression = operator.getExpression(firstOperand, secondOperand);
        expressionsList.remove(operatorIndex + 1);
        expressionsList.set(operatorIndex, resultExpression);
        operatorList.remove(operatorIndex);
    }

    private int getFirstIndexOfOperationInPriority(int priority) {
        return IntStream.range(0, operatorList.size())
                .filter(i -> operatorList.get(i).getPriority() == priority)
                .findFirst()
                .orElse(-1);
    }
}
