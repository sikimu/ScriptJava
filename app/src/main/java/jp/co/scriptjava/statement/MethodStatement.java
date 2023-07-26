package jp.co.scriptjava.statement;

import java.util.List;

import jp.co.scriptjava.lexical.LexicalBlock;
import jp.co.scriptjava.lexical.LexicalMultiBlock;
import jp.co.scriptjava.lexical.LexicalSingleBlock;

public class MethodStatement extends Statement {

    /**
     * メソッド名
     */
    final public String methodName;

    /**
     * ステートメントリスト
     */
    final public List<Statement> statementList;

    public MethodStatement(LexicalSingleBlock definitionBlock, LexicalBlock lexicalBlock) {

        // メソッド名を取得する
        methodName = definitionBlock.get(definitionBlock.size() - 1).value;

        // ステートメントリストを作成する
        statementList = StatementStructure.structure((LexicalMultiBlock)lexicalBlock);
    }
}
