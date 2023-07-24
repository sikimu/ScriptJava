package jp.co.scriptjava.lexical;

import java.util.ArrayList;
import java.util.List;


/**
 * 字句を構造的にブロック化したものを作る
 */
public class LexicalBlockStructure {

    public static LexicalRootBlock structure(List<Lexical> list){

        // コメント以外をステートメントリストに変換する
        List<Lexical> lexicals = removeComment(list);

        // ブロックを作る
        List<LexicalBlock> blocks = structure(lexicals, 0);

        return new LexicalRootBlock(blocks); 
    }

    // ブロックを作る
    private static List<LexicalBlock> structure(List<Lexical> lexicals, int index) {
        List<LexicalBlock> blocks = new ArrayList<LexicalBlock>();

        while(index < lexicals.size()){
            Lexical lexical = lexicals.get(index);

            if(lexical.value.equals("{")){
                int end = findBrace(lexicals, index);

                if (end == -1) {
                    // }がない場合は、最後までをブロック化する
                    blocks.add(new LexicalSingleBlock(lexicals.subList(index, lexicals.size())));
                    break;
                } else {
                    // }がある場合は、}までをブロック化する
                    blocks.add(new LexicalMultiBlock(structure(lexicals.subList(index + 1, end), 0)));
                    index = end + 1;
                }
            }
            else{
                int end = findSemicolonOrBrace(lexicals, index);

                if (end == -1) {
                    // セミコロンがない場合は、最後までをブロック化する
                    blocks.add(new LexicalSingleBlock(lexicals.subList(index, lexicals.size())));
                    break;
                } else {
                    // セミコロンがある場合は、セミコロンまでをブロック化する
                    blocks.add(new LexicalSingleBlock(lexicals.subList(index, end + 1)));
                    index = end + 1;
                }
            }
        }

        return blocks;
    }

    // }までを探す
    private static int findBrace(List<Lexical> lexicals, int index) {

        int count = 0;

        for (int i = index; i < lexicals.size(); i++) {
            Lexical lexical = lexicals.get(i);
            if (lexical.value.equals("{")) {
                count++;
            }
            if (lexical.value.equals("}")) {
                count--;
                if(count == 0){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * セミコロンか括弧を探す
     * @param lexicals
     * @param index
     * @return
     */
    private static int findSemicolonOrBrace(List<Lexical> lexicals, int index) {
        for (int i = index; i < lexicals.size(); i++) {
            Lexical lexical = lexicals.get(i);
            if (lexical.value.equals(";")) {
                return i;
            }
            if (lexical.value.equals("{")) {
                return i - 1;// 括弧は含めいない
            }
        }
        return -1;
    }


    /**
     * コメントを除外する
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
}
