package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.block.Block;
import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.block.SingleBlock;

public class ClassStatement extends Statement{

    /**
     * クラス名
     */
    final public String className;

    /**
     * ステートメントリスト
     */
    final public List<Statement> statementList;
    
    public ClassStatement(SingleBlock definitionBlock, MultiBlock lexicalBlock) {

        // クラス名を取得する
        className = definitionBlock.get(definitionBlock.size() -1).value;

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
            
            // enum
            if(singleBlock.get(0).value.equals("enum") || singleBlock.get(1).value.equals("enum")){
                statementList.add(new EnumStatement(singleBlock, (MultiBlock) block.children.get(index + 1)));
                index += 2;
            }
            // 処理文
            else if (singleBlock.get(singleBlock.size() - 1).value.equals(";")) {
                statementList.add(new ProcessStatement(singleBlock));
                index++;
            }
            // メソッド(次のブロックがMultiBlockの場合はメソッド)
            else if (block.children.get(index + 1) instanceof MultiBlock) {
                statementList.add(new MethodStatement(singleBlock, (MultiBlock) block.children.get(index + 1)));
                index += 2;
            }            
            else {
                throw new RuntimeException("想定外のブロックです。:" + lexicalBlock.toString());
            }
        }

        return statementList;
    }
}
