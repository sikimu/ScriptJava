package jp.co.scriptjava.statement;

import jp.co.scriptjava.ExpressionContainer;
import jp.co.scriptjava.block.SingleBlock;

public class ReturnStatement extends Statement{

    /**
     * 計算式
     * return expression;
     */
    public final ExpressionContainer expressionContainer;

    public ReturnStatement(SingleBlock singleBlock) {
        expressionContainer = new ExpressionContainer(singleBlock.subList(1, singleBlock.size()));
    }

}
