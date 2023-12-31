package jp.co.scriptjava.lexical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicalList {
    /**
     * 改行文字
     */
    public static final List<String> LINE_TERMINATORS = Arrays.asList("\n", "\r");

    /**
     * 空白文字
     */
    public static final List<String> WHITE_SPACE = Arrays.asList(" ", "\t");

    /**
     * 区切り文字
     */
    public static final List<String> SEPARATORS = Arrays.asList("(", ")", "{", "}", "[", "]", ".", ";", ",", "...",
            "::", "@");

    /**
     * 演算子
     */
    public static final List<String> OPERATORS = Arrays.asList(
            "=", ">", "<", "!", "~", "?", ":", "->",
            "==", ">=", "<=", "!=", "&&", "||", "++", "--",
            "+", "-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>",
            "+=", "-=", "*=,", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>=");

    private List<String> lexicals;
            
    /**
     * コンストラクタ
     */
    public LexicalList(String source) {
        lexicals = new ArrayList<String>();

        // 最初の文字までの空白と改行を飛ばす
        int index = nextTokenIndex(source, 0);
        while (index < source.length()) {
            int end = nextTokenEndIndex(source, index);

            String str = source.substring(index, end);

            lexicals.add(str);

            index = nextTokenIndex(source, end);
        }
    }

    // 次の字句の開始位置を返す
    private static int nextTokenIndex(String source, final int index) {

        int cnt = 0;
        while (index < source.length()) {

            final int pos = index + cnt;

            if (LINE_TERMINATORS.stream().anyMatch(str -> source.startsWith(str, pos)) ||
                    WHITE_SPACE.stream().anyMatch(str -> source.startsWith(str, pos))) {
                cnt++;
            } else {
                break;
            }
        }
        return index + cnt;
    }

    // 字句の終了位置を返す
    private static int nextTokenEndIndex(String source, final int index) {

        // コメントの場合
        if (source.startsWith("//", index)) {
            int cnt = 2;
            while (index + cnt < source.length()) {

                final int pos = index + cnt;

                if (LINE_TERMINATORS.stream().anyMatch(str -> source.startsWith(str, pos))) {
                    break;
                }
                cnt++;
            }
            return index + cnt;
        }
        // 複数行コメントの場合
        if (source.startsWith("/*", index)) {
            int cnt = 2;
            while (index + cnt < source.length()) {
                if (source.startsWith("*/", index + cnt)) {
                    cnt += 2;
                    break;
                }
                cnt++;
            }
            return index + cnt;
        }

        // 開始文字がStringLiteralの場合
        // 開始文字がCharacterLiteralの場合
        if (source.startsWith("\"", index) || source.startsWith("'", index)) {
            String start = source.substring(index, index + 1);
            int cnt = 1;
            while (index + cnt < source.length()) {
                if (source.startsWith("\\", index + cnt)) {
                    cnt++;
                } else if (source.startsWith(start, index + cnt)) {
                    cnt++;
                    break;
                }
                cnt++;
            }
            return index + cnt;
        }

        // 単語として認識される文字列のリスト
        ArrayList<String> tokenString = new ArrayList<String>();
        tokenString.addAll(SEPARATORS);
        tokenString.addAll(OPERATORS);
        tokenString.sort((a, b) -> b.length() - a.length());

        for (String str : tokenString) {
            if (source.startsWith(str, index)) {
                return index + str.length();
            }
        }

        // 終了文字一覧を文字の降順にソートしたリストの作成
        ArrayList<String> endStrings = new ArrayList<String>();
        endStrings.addAll(tokenString);
        endStrings.addAll(LINE_TERMINATORS);
        endStrings.addAll(WHITE_SPACE);
        endStrings.add("\"");
        endStrings.add("'");
        endStrings.sort((a, b) -> b.length() - a.length());

        // 識別子
        int cnt = 0;
        while (index + cnt < source.length()) {

            final int pos = index + cnt;

            if (endStrings.stream().anyMatch(str -> source.startsWith(str, pos))) {
                break;
            } else {
                cnt++;
            }
        }
        return index + cnt;
    }

    public String get(int i) {
        return lexicals.get(i);
    }    
}
