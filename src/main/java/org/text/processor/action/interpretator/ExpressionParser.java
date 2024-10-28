package org.text.processor.action.interpretator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.text.processor.utils.TextValidator;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    //(7^5|1&2<<(2|5>>2&71))|1200
    // 1)  "2 | 5 >> 2 & 71" :

    // 1.1) 5 >> 2
    // 1.2) [1.1] & 71
    // 1.3) 2 | [1.2]

    //2) 7 ^ 5 | 1 & 2<<[1.3] :

    //2.1) 2 << [1.3]
    //2.2) 1 & [2.1]
    //2.3) 7 ^ 5
    //2.4) [2.3] | [2.2]

    //3) [2.4] | 1200
    private Logger logger = LogManager.getLogger(this.getClass());
    private List<Expression> numberExpressions = new ArrayList<>();
    private List<BitwiseOperator> operationExpressions = new ArrayList<>();

    private final String stringExpression;

    public ExpressionParser(String stringExpression) {
        if (TextValidator.isValidExpression(stringExpression)) {
            this.stringExpression = stringExpression;
        } else {
            logger.log(Level.ERROR, "Wrong expression: " + stringExpression);
            throw new IllegalArgumentException("Wrong expression: " + stringExpression);
        }
    }

    private void parse() {

    }
}
























