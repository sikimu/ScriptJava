package jp.co.scriptjava.block;

import java.util.List;

/**
 * 字句ブロックをもつブロック
 */
public class LexicalMultiBlock extends Block{
 
    /** ブロックリスト */
    public final List<Block> children;
    
    /** コンストラクタ */
    public LexicalMultiBlock(List<Block> children) {
        this.children = children;
    }
}
