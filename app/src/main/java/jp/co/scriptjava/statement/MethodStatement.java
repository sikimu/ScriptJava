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
     * ステートメントリスト
     */
    final public List<Statement> statementList;

    public MethodStatement(LexicalSingleBlock definitionBlock, LexicalMultiBlock lexicalBlock) {

        // メソッド名を取得する
        methodName = definitionBlock.get(definitionBlock.size() - 1).value;

        // ステートメントリストを作成する
        statementList = createStatementList(lexicalBlock);
    }

    private List<Statement> createStatementList(LexicalMultiBlock block) {
        List<Statement> statementList = new ArrayList<Statement>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < block.children.size()) {

            LexicalBlock lexicalBlock = block.children.get(index);

            LexicalSingleBlock singleBlock = (LexicalSingleBlock)lexicalBlock;
            
            // 処理文
            if (singleBlock.get(singleBlock.size() - 1).value.equals(";")) {
                statementList.add(new ProcessStatement(singleBlock));
                index++;
            }
            else {
                throw new RuntimeException("想定外のブロックです。:" + lexicalBlock.toString());
            }
        }

        return statementList;
    }
}
