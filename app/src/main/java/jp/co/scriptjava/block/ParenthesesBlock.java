package jp.co.scriptjava.block;

import java.util.List;

/**
 * ()ブロックをもつブロック
 */
public class ParenthesesBlock extends Block{
 
    /** ブロックリスト */
    public final List<Block> children;
    
    /** コンストラクタ */
    public ParenthesesBlock(List<Block> children) {
        this.children = children;
    }
}
