package jp.co.scriptjava.statement;

import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.lexical.Lexical;


public class StatementStructure {
    /**
     * 字句リストからステートメントリストに変換する
     * 
     * @param source
     * @return
     */
    public static List<Statement> structure(List<Lexical> lexicalList) {

        List<Statement> statementList = new ArrayList<Statement>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while(index < lexicalList.size()) {

            // ステートメントの最後の字句のインデックスを取得する
            int end = nextStatementEndIndex(lexicalList, index);

            List<Lexical> list = lexicalList.subList(index, end);

            // ステートメントを作成する
            Statement statement = createStatement(list);
            // ステートメントを追加する
            statementList.add(statement);

            // ステートメントの最後の字句のインデックスを次のステートメントの最初の字句のインデックスにする
            index = end;
        }

        return statementList;
    }

    private static int nextStatementEndIndex(List<Lexical> lexicalList, int index) {

        int end = index;
        while(end < lexicalList.size()) {
            Lexical lexical = lexicalList.get(end);
            if(lexical.toString().equals(";")) {
                break;
            }
            end++;
        }

        return end + 1;
    }

    private static Statement createStatement(List<Lexical> lexicalList) {

        return new Statement(lexicalList);
    }  
}
