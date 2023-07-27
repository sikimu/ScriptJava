package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.SingleBlock;

public class ProcessStatement extends Statement{

    /**
     * 処理文
     */
    final public String process;

    public ProcessStatement(SingleBlock singleBlock) {
        this.process = singleBlock.toString();
    }
}
