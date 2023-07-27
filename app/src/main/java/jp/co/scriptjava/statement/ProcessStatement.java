package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.LexicalSingleBlock;

public class ProcessStatement extends Statement{

    /**
     * 処理文
     */
    final public String process;

    public ProcessStatement(LexicalSingleBlock singleBlock) {
        this.process = singleBlock.toString();
    }
}
