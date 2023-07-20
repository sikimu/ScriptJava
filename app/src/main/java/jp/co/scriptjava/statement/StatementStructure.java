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

        // コメント以外をステートメントリストに変換する
        List<Lexical> list = removeComment(lexicalList);

        // ステートメント単位に分割する
        List<List<Lexical>> lexicalBlockList = splitStatement(list);

        // ステートメントリストを作成する
        List<Statement> statementList = createStatementList(lexicalBlockList);

        return statementList;
    }

    /**
     * コメント以外をステートメントリストに変換する
     * 
     * @param lexicalList
     * @return
     */
    private static List<Lexical> removeComment(List<Lexical> lexicalList) {

        List<Lexical> list = new ArrayList<Lexical>();

        for (Lexical lexical : lexicalList) {
            if (lexical.type != Lexical.TYPE.COMMENT) {
                list.add(lexical);
            }
        }

        return list;
    }

    private static List<Statement> createStatementList(List<List<Lexical>> lexicalBlockList) {

        List<Statement> statementList = new ArrayList<Statement>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < lexicalBlockList.size()) {

            List<Lexical> lexicalList = lexicalBlockList.get(index);

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
        return new unknownStatement(lexicalList);
    }

    /**
     * ステートメント単位に分割する
     */
    private static List<List<Lexical>> splitStatement(List<Lexical> lexicalList) {

        List<List<Lexical>> list = new ArrayList<List<Lexical>>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < lexicalList.size()) {

            // ステートメントの最後の字句のインデックスを取得する
            int end = nextStatementEndIndex(lexicalList, index);

            List<Lexical> l = lexicalList.subList(index, end);
            list.add(l);

            // ステートメントの最後の字句のインデックスを次のステートメントの最初の字句のインデックスにする
            index = end;
        }

        return list;
    }

    private static int nextStatementEndIndex(List<Lexical> lexicalList, int index) {

        // {}括弧の場合それだけのステートメント
        if (lexicalList.get(index).type == Lexical.TYPE.SEPARATOR) {
            if (lexicalList.get(index).value.equals("{") || lexicalList.get(index).value.equals("}")) {
                return index + 1;
            }
        }

        int end = index;
        while (end < lexicalList.size()) {
            Lexical lexical = lexicalList.get(end);
            // {}は次のステートメントにする
            if (lexical.type == Lexical.TYPE.SEPARATOR) {
                if (lexical.value.equals("{") || lexical.value.equals("}")) {
                    break;
                }
            }
            // ;はステートメントの終わり
            if (lexical.type == Lexical.TYPE.SEPARATOR && lexical.value.equals(";")) {
                end++;
                break;
            }
            end++;
        }

        return end;
    }
}
