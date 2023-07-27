package jp.co.scriptjava.block;

import java.util.List;

import jp.co.scriptjava.lexical.Lexical;

/**
 * 字句リストをもつブロック
 */
public class LexicalSingleBlock extends Block{
    
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

    /**
     * 対象の字句を含んでいるかどうかを判定する
     * @param lexical
     * @return
     */
    public boolean contains(Lexical lexical) {
        return lexicals.contains(lexical);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof LexicalSingleBlock){
            return lexicals.equals(((LexicalSingleBlock)obj).lexicals);
        }
        return false;
    }
}
