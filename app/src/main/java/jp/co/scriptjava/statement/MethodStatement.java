package jp.co.scriptjava.statement;

import java.util.List;

import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.block.SingleBlock;

public class MethodStatement extends Statement {

    /**
     * 戻り値の型
     */
    final public String returnType;

    /**
     * 引数リスト
     */
    final public List<JsArgument> arguments;

    /**
     * メソッド名
     */
    final public String name;

    /**
     * {}の中のステートメントリスト
     */
    final public CompoundStatement compound;

    public MethodStatement(SingleBlock definitionBlock, MultiBlock lexicalBlock) {

        // メソッドの戻り値の型を取得する
        returnType = definitionBlock.get(definitionBlock.indexof("(") - 2).value;

        // メソッド名を取得する
        name = definitionBlock.get(definitionBlock.indexof("(") - 1).value;

        // メソッドの引数リストを取得する
        arguments = getArgumentList(definitionBlock);

        // {}の中のステートメントリストを作成する
        compound = new CompoundStatement(lexicalBlock);
    }

    private List<JsArgument> getArgumentList(SingleBlock definitionBlock) {
        return null;
    }
}
