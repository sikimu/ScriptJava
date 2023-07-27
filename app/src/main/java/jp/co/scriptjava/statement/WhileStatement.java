package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.Block;
import jp.co.scriptjava.block.LexicalSingleBlock;

public class WhileStatement extends Statement{

    /**
     * 条件式
     */
    final public String condition;

    public WhileStatement(LexicalSingleBlock singleBlock, Block whileBlock) {
        this.condition = singleBlock.toString();
    }
}
