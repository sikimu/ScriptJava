package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.block.Block;
import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.block.SingleBlock;

public class CompoundStatement extends Statement{

    /**
     * ステートメントリスト
     */
    final public List<Statement> statementList;

    public CompoundStatement(MultiBlock lexicalBlock) {

        // ステートメントリストを作成する
        statementList = createStatementList(lexicalBlock);
    }

    private List<Statement> createStatementList(MultiBlock block) {
        List<Statement> statementList = new ArrayList<Statement>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < block.children.size()) {

            Block lexicalBlock = block.children.get(index);

            SingleBlock singleBlock = (SingleBlock)lexicalBlock;
            
            // 処理文
            if (singleBlock.get(singleBlock.size() - 1).value.equals(";")) {
                statementList.add(new ProcessStatement(singleBlock));
                index++;
            }
            // while文
            else if (singleBlock.get(0).value.equals("while")) {
                Block whileBlock = (Block)block.children.get(index + 1);
                statementList.add(new WhileStatement(singleBlock, whileBlock));
                index += 2;
            }
            // for文
            else if (singleBlock.get(0).value.equals("for")) {
                Block forBlock = (Block)block.children.get(index + 1);
                statementList.add(new ForStatement(singleBlock, forBlock));
                index += 2;
            }
            // if文
            else if (singleBlock.get(0).value.equals("if")) {
                Block ifBlock = (Block)block.children.get(index + 1);
                statementList.add(new IfStatement(singleBlock, ifBlock));
                index += 2;
            }
            else {
                throw new RuntimeException("想定外のブロックです。:" + lexicalBlock.toString());
            }
        }

        return statementList;
    }
}
