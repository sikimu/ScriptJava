package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.lexical.LexicalBlock;
import jp.co.scriptjava.lexical.LexicalMultiBlock;
import jp.co.scriptjava.lexical.LexicalRootBlock;
import jp.co.scriptjava.lexical.LexicalSingleBlock;

public class StatementStructure {
    /**
     * 字句リストからステートメントリストに変換する
     * 
     * @param source
     * @return
     */
    public static List<Statement> structure(LexicalRootBlock block) {

        // ステートメントリストを作成する
        List<Statement> statementList = createStatementList(block);

        return statementList;
    }

    private static List<Statement> createStatementList(LexicalRootBlock block) {

        List<Statement> statementList = new ArrayList<Statement>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < block.children.size()) {

            // ステートメントを作成する
            Statement statement = createStatement(block.children.get(index));
            // ステートメントを追加する
            statementList.add(statement);

            index++;
        }

        return statementList;
    }

    private static Statement createStatement(LexicalBlock lexicalBlock) {

        if(lexicalBlock instanceof LexicalSingleBlock){
            LexicalSingleBlock singleBlock = (LexicalSingleBlock)lexicalBlock;
            // import文
            if (singleBlock.get(0).value.equals("import")) {
                return new ImportStatement(singleBlock);
            }
            // package文
            if (singleBlock.get(0).value.equals("package")) {
                return new PackageStatement(singleBlock);
            }
            return new unknownStatement(singleBlock);
        }
        else{
            LexicalMultiBlock multiBlock = (LexicalMultiBlock)lexicalBlock;
            return createStatement(multiBlock.children.get(0));
        }
    }
}
