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

            if(end == lexicalList.size()) {
                break;
            }

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

        // {}括弧の場合それだけのステートメント
        if(lexicalList.get(index).type == Lexical.TYPE.SEPARATOR) {
            if(lexicalList.get(index).value.equals("{") || lexicalList.get(index).value.equals("}")) {            
                return index + 1;
            }
        }

        int end = index;
        while(end < lexicalList.size()) {
            Lexical lexical = lexicalList.get(end);
            //{}は次のステートメントにする
            if(lexical.type == Lexical.TYPE.SEPARATOR) {
                if(lexical.value.equals("{") || lexical.value.equals("}")) {            
                    break;
                }
            }
            // ;はステートメントの終わり
            if(lexical.type == Lexical.TYPE.SEPARATOR && lexical.value.equals(";")) {
                end++;
                break;
            }
            end++;
        }

        return end;
    }

    private static Statement createStatement(List<Lexical> lexicalList) {

        return new Statement(lexicalList);
    }  
}
