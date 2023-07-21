package jp.co.scriptjava.lexical;

import java.util.ArrayList;
import java.util.List;

/**
 * 字句を構造的にブロック化したものを作る
 */
public class LexicalBlockStructure {

    public static LexicalRootBlock structure(List<Lexical> lexicals){

        // コメント以外をステートメントリストに変換する
        List<Lexical> list = removeComment(lexicals);

        List<LexicalSingleBlock> blocks = new ArrayList<LexicalSingleBlock>();

        // ステートメントの最初の字句のインデックス
        int index = 0;
        while (index < list.size()) {

            // ステートメントの最後の字句のインデックスを取得する
            int end = nextStatementEndIndex(list, index);

            List<Lexical> l = list.subList(index, end);
            blocks.add(new LexicalSingleBlock(l));

            // ステートメントの最後の字句のインデックスを次のステートメントの最初の字句のインデックスにする
            index = end;
        }

        LexicalRootBlock lexicalBlock = new LexicalRootBlock(blocks);        
        return lexicalBlock;
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
