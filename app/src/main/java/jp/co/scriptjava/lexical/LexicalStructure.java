package jp.co.scriptjava.lexical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicalStructure {

    /**
     * 改行文字
     */
    public static final List<Character> LINE_TERMINATORS = Arrays.asList('\n', '\r');

    /**
     * 空白文字
     */
    public static final List<Character> WHITE_SPACE = Arrays.asList(' ', '\t');

    /**
     * 区切り文字
     */
    public static final List<String> SEPARATORS = Arrays.asList("(", ")", "{", "}", "[", "]", ".", ";", ",", "...", "::", "@");

    /**
     * ソースを字句リストに変換する
     * @param source
     * @return
     */
    public static List<Lexical> structure(String source) {
        
        List<Lexical> lexicals = new ArrayList<Lexical>();

        // 最初の文字までの空白と改行を飛ばす
        int index = nextTokenIndex(source, 0);
        while (index < source.length()) {
            int end = nextTokenEndIndex(source, index);

            String str = source.substring(index, end);

            lexicals.add(createLexical(str));

            index = nextTokenIndex(source, end);
        }

        return lexicals;
    }

    // 字句を作成する
    private static Lexical createLexical(String str) {
        return new Identifier(str);
    }

    // 次の字句の開始位置を返す
    private static int nextTokenIndex(String source, int index) {
        while (index < source.length()) {
            char c = source.charAt(index);
            if (LINE_TERMINATORS.contains(c) || WHITE_SPACE.contains(c)) {
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    //　字句の終了位置を返す
    private static int nextTokenEndIndex(String source, final int index) {
        
        // 終了文字一覧を文字の降順にソートしたリストの作成
        ArrayList<String> endStrings = new ArrayList<String>();
        endStrings.addAll(SEPARATORS);
        endStrings.sort((a, b) -> b.length() - a.length());

        for (String str : endStrings) {
            if (source.startsWith(str, index)) {
                return index + str.length();
            }
        }        
        
        int cnt = 0;
        while (index + cnt < source.length()) {

            final int pos = index + cnt;

            char c = source.charAt(pos);
            if (LINE_TERMINATORS.contains(c) || WHITE_SPACE.contains(c)) {
                break;
            } 
            //文字数の昇順で判定
            else if (endStrings.stream().anyMatch(str -> source.startsWith(str, pos))) {
                break;
            }
            else {
                cnt++;
            }
        }
        return index + cnt;
    }
}
