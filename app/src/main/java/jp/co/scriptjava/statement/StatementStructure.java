package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.block.LexicalBlock;
import jp.co.scriptjava.block.LexicalMultiBlock;
import jp.co.scriptjava.block.LexicalSingleBlock;
import jp.co.scriptjava.lexical.Lexical;

public class StatementStructure {
    /**
     * 字句リストからステートメントリストに変換する
     * 
     * @param source
     * @return
     */
    public static List<Statement> structure(LexicalMultiBlock block) {

        // ステートメントリストを作成する
        List<Statement> statementList = createStatementList(block);

        return statementList;
    }

    private static List<Statement> createStatementList(LexicalMultiBlock block) {

        List<Statement> statementList = new ArrayList<Statement>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < block.children.size()) {

            LexicalBlock lexicalBlock = block.children.get(index);

            LexicalSingleBlock singleBlock = (LexicalSingleBlock)lexicalBlock;
            // import文
            if (singleBlock.get(0).value.equals("import")) {
                statementList.add(new ImportStatement(singleBlock));
                index++;
            }
            // package文
            else if (singleBlock.get(0).value.equals("package")) {
                statementList.add(new PackageStatement(singleBlock));
                index++;
            }
            // class文
            else if (singleBlock.contains(new Lexical(Lexical.TYPE.IDENTIFIER, "class"))) {
                LexicalMultiBlock classBlock = (LexicalMultiBlock)block.children.get(index + 1);
                statementList.add(new ClassStatement(singleBlock, classBlock));
                index += 2;
            }
            else {
                throw new RuntimeException("想定外のブロックです。:" + lexicalBlock.toString());
            }

            
        }

        return statementList;
    }
}
