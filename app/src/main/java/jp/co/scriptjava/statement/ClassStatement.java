package jp.co.scriptjava.statement;

import java.util.List;

import jp.co.scriptjava.lexical.LexicalBlock;
import jp.co.scriptjava.lexical.LexicalMultiBlock;
import jp.co.scriptjava.lexical.LexicalSingleBlock;

public class ClassStatement extends Statement{

    /**
     * クラス名
     */
    final public String className;

    /**
     * ステートメントリスト
     */
    final public List<Statement> statementList;
    
    public ClassStatement(LexicalSingleBlock definitionBlock, LexicalBlock lexicalBlock) {

        // クラス名を取得する
        className = definitionBlock.get(definitionBlock.size() -1).value;

        // ステートメントリストを作成する
        statementList = StatementStructure.structure((LexicalMultiBlock)lexicalBlock);
    }

}
