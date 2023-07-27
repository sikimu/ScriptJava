package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.block.LexicalBlock;
import jp.co.scriptjava.block.LexicalMultiBlock;
import jp.co.scriptjava.block.LexicalSingleBlock;

public class MethodStatement extends Statement {

    /**
     * メソッド名
     */
    final public String methodName;

    /**
     * {}の中のステートメントリスト
     */
    final public CompoundStatement compound;

    public MethodStatement(LexicalSingleBlock definitionBlock, LexicalMultiBlock lexicalBlock) {

        // メソッド名を取得する
        methodName = definitionBlock.get(definitionBlock.size() - 1).value;

        // {}の中のステートメントリストを作成する
        compound = new CompoundStatement(lexicalBlock);
    }
}
