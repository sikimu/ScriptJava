package jp.co.scriptjava.block;

import java.util.List;

/**
 * 字句ブロックをもつブロック
 */
public class MultiBlock extends Block{
 
    /** ブロックリスト */
    public final List<Block> children;
    
    /** コンストラクタ */
    public MultiBlock(List<Block> children) {
        this.children = children;
    }
}
