package jp.co.scriptjava.lexical;

import java.util.List;

/**
 * 字句リストをもつブロック
 */
public class LexicalSingleBlock extends LexicalBlock{
    
    /** 字句リスト */
    private final List<Lexical> lexicals;

    /** コンストラクタ */
    public LexicalSingleBlock(List<Lexical> lexicals) {
        this.lexicals = lexicals;
    }

    /** 字句リストを取得する */
    public Lexical get(int index) {
        return lexicals.get(index);
    }

    @Override
    public String toString() {
        return lexicals.toString();
    }

    public int size() {
        return lexicals.size();
    }

    public List<Lexical> subList(int i, int j) {
        return lexicals.subList(i, j);
    }
}
