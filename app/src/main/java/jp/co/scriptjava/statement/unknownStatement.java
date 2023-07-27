package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.SingleBlock;

public class unknownStatement extends Statement{
    SingleBlock lexicalSingleBlock;
    
    public unknownStatement(SingleBlock lexicalSingleBlock) {
        this.lexicalSingleBlock = lexicalSingleBlock;
    }

    @Override
    public String toString() {
        return lexicalSingleBlock.toString();
    }    
}
