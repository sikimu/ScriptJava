package jp.co.scriptjava.lexical;

import java.util.List;

/**
 * 字句ブロックをもつブロック
 */
public class LexicalMultiBlock extends LexicalBlock{
 
    /** ブロックリスト */
    public final List<LexicalBlock> children;
    
    /** コンストラクタ */
    public LexicalMultiBlock(List<LexicalBlock> children) {
        this.children = children;
    }
}
