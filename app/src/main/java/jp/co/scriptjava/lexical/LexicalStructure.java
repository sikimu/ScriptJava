package jp.co.scriptjava.lexical;

import java.util.ArrayList;
import java.util.List;

public class LexicalStructure {

    public static List<Lexical> structure(String source) {
        
        List<Lexical> lexicals = new ArrayList<Lexical>();

        // 最初の文字までの空白と改行を飛ばす
        int index = nextTokenIndex(source, 0);
        int end = nextTokenEndIndex(source, index);

        Identifier identifier = new Identifier(source.substring(index, end));
        lexicals.add(identifier);

        return lexicals;
    }

    // 次の字句の開始位置を返す
    private static int nextTokenIndex(String source, int index) {
        while (index < source.length()) {
            char c = source.charAt(index);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    //　字句の終了位置を返す
    private static int nextTokenEndIndex(String source, int index) {
        index++;
        while (index < source.length()) {
            char c = source.charAt(index);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                break;
            } else {
                index++;
            }
        }
        return index;
    }
}
