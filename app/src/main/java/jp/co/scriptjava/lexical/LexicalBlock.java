package jp.co.scriptjava.lexical;

import java.util.List;

/**
 * 字句を構造的にブロック化したもの
 */
public class LexicalBlock {
    
    /** 字句リスト */
    public final List<Lexical> lexicals;
    
    /** ブロックリスト */
    public final List<LexicalBlock> children;
    
    /** コンストラクタ */
    public LexicalBlock(List<Lexical> lexicals, List<LexicalBlock> children) {
        this.lexicals = lexicals;
        this.children = children;
    }

    @Override
    public String toString() {
        return "LexicalBlock [lexicals=" + lexicals + ", blocks=" + children + "]";
    }
}
