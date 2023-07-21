package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalRootBlock;

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

            List<Lexical> lexicalList = block.children.get(index).lexicals;

            // ステートメントを作成する
            Statement statement = createStatement(lexicalList);
            // ステートメントを追加する
            statementList.add(statement);

            index++;
        }

        return statementList;
    }

    private static Statement createStatement(List<Lexical> lexicalList) {

        // import文
        if (lexicalList.get(0).value.equals("import")) {
            return new ImportStatement(lexicalList);
        }
        // package文
        if (lexicalList.get(0).value.equals("package")) {
            return new PackageStatement(lexicalList);
        }
        return new unknownStatement(lexicalList);
    }
}
