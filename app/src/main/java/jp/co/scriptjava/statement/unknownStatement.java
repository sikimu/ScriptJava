package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.LexicalSingleBlock;

public class unknownStatement extends Statement{
    LexicalSingleBlock lexicalSingleBlock;
    
    public unknownStatement(LexicalSingleBlock lexicalSingleBlock) {
        this.lexicalSingleBlock = lexicalSingleBlock;
    }

    @Override
    public String toString() {
        return lexicalSingleBlock.toString();
    }    
}
