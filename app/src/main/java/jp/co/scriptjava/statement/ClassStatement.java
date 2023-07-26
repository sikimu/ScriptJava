package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.block.LexicalBlock;
import jp.co.scriptjava.block.LexicalMultiBlock;
import jp.co.scriptjava.block.LexicalSingleBlock;

public class ClassStatement extends Statement{

    /**
     * クラス名
     */
    final public String className;

    /**
     * ステートメントリスト
     */
    final public List<Statement> statementList;
    
    public ClassStatement(LexicalSingleBlock definitionBlock, LexicalMultiBlock lexicalBlock) {

        // クラス名を取得する
        className = definitionBlock.get(definitionBlock.size() -1).value;

        // ステートメントリストを作成する
        statementList = createStatementList(lexicalBlock);
    }

    private List<Statement> createStatementList(LexicalMultiBlock block) {
        List<Statement> statementList = new ArrayList<Statement>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < block.children.size()) {

            LexicalBlock lexicalBlock = block.children.get(index);

            if (lexicalBlock instanceof LexicalSingleBlock == false) {
                throw new RuntimeException("想定外のブロックです。:" + lexicalBlock.toString());
            }

            LexicalSingleBlock singleBlock = (LexicalSingleBlock)lexicalBlock;
            
            // メソッド
            if (singleBlock.get(singleBlock.size() - 1).value.equals(")")) {
                LexicalMultiBlock methodBlock = (LexicalMultiBlock)block.children.get(index + 1);
                statementList.add(new MethodStatement(singleBlock, methodBlock));
                index += 2;
            }
            else {
                throw new RuntimeException("想定外のブロックです。:" + lexicalBlock.toString());
            }
        }

        return statementList;
    }
}
