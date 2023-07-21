package jp.co.scriptjava.lexical;

import java.util.List;

/**
 * 字句リストをもつブロック
 */
public class LexicalSingleBlock extends LexicalBlock{
    
    /** 字句リスト */
    public final List<Lexical> lexicals;

    /** コンストラクタ */
    public LexicalSingleBlock(List<Lexical> lexicals) {
        this.lexicals = lexicals;
    }

    @Override
    public String toString() {
        return lexicals.toString();
    }
}
