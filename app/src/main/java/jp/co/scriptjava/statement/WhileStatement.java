package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.Block;
import jp.co.scriptjava.block.SingleBlock;

public class WhileStatement extends Statement{

    /**
     * 条件式
     */
    final public String condition;

    public WhileStatement(SingleBlock singleBlock, Block whileBlock) {
        this.condition = singleBlock.toString();
    }
}
