package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.block.SingleBlock;

public class MethodStatement extends Statement {

    /**
     * メソッドの戻り値の型
     */
    final public String returnType;

    /**
     * メソッド名
     */
    final public String methodName;

    /**
     * {}の中のステートメントリスト
     */
    final public CompoundStatement compound;

    public MethodStatement(SingleBlock definitionBlock, MultiBlock lexicalBlock) {

        // メソッドの戻り値の型を取得する
        returnType = definitionBlock.get(definitionBlock.indexof("(") - 2).value;

        // メソッド名を取得する
        methodName = definitionBlock.get(definitionBlock.indexof("(") - 1).value;

        // {}の中のステートメントリストを作成する
        compound = new CompoundStatement(lexicalBlock);
    }
}
