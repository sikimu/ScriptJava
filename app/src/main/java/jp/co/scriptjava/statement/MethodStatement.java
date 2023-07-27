package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.MultiBlock;
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

    public MethodStatement(LexicalSingleBlock definitionBlock, MultiBlock lexicalBlock) {

        // メソッド名を取得する
        methodName = definitionBlock.get(definitionBlock.size() - 1).value;

        // {}の中のステートメントリストを作成する
        compound = new CompoundStatement(lexicalBlock);
    }
}
