package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.SingleBlock;

public class ProcessStatement extends Statement{

    /**
     * 処理文ブロック
     */
    final public SingleBlock block;

    public ProcessStatement(SingleBlock block) {

        this.block = block;
    }
}
