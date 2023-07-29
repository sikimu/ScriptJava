package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.SingleBlock;

public class UnknownStatement extends Statement{
    SingleBlock lexicalSingleBlock;
    
    public UnknownStatement(SingleBlock lexicalSingleBlock) {
        this.lexicalSingleBlock = lexicalSingleBlock;
    }

    @Override
    public String toString() {
        return lexicalSingleBlock.toString();
    }    
}
