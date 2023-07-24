package jp.co.scriptjava.lexical;

import java.util.List;

/**
 * 字句を構造的にブロック化したもの
 */
public class LexicalRootBlock {
    
    /** ブロックリスト */
    public final List<LexicalBlock> children;
    
    /** コンストラクタ */
    public LexicalRootBlock(List<LexicalBlock> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "LexicalRootBlock [blocks=" + children + "]";
    }
}
