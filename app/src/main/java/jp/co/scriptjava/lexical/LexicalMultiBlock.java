package jp.co.scriptjava.lexical;

import java.util.List;

/**
 * 字句ブロックをもつブロック
 */
public class LexicalMultiBlock extends LexicalBlock{
 
    /** ブロックリスト */
    public final List<LexicalSingleBlock> children;
    
    /** コンストラクタ */
    public LexicalMultiBlock(List<LexicalSingleBlock> children) {
        this.children = children;
    }
}
