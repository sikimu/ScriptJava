package jp.co.scriptjava.block;

import java.util.List;

/**
 * {}ブロックをもつブロック
 */
public class BracesBlock extends Block{
 
    /** ブロックリスト */
    public final List<Block> children;
    
    /** コンストラクタ */
    public BracesBlock(List<Block> children) {
        this.children = children;
    }
}
