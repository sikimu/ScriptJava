package jp.co.scriptjava.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;

public class LexicalBlockStructureTest {

    @Test
    void 括弧なし基本パターン() {
        List<Lexical> lexicals = LexicalStructure.structure("a+b;c+d;");   
        
        BracesBlock block = BlockStructure.structure(lexicals);
        assertEquals(2, block.children.size());
        assertEquals(new SingleBlock(LexicalStructure.structure("a+b;")), block.children.get(0));
        assertEquals(new SingleBlock(LexicalStructure.structure("c+d;")), block.children.get(1));
    }

    @Test
    void 括弧あり基本パターン() {
        List<Lexical> lexicals = LexicalStructure.structure("a+b;{c+d;}e+f;");   
        
        BracesBlock block = BlockStructure.structure(lexicals);
        assertEquals(3, block.children.size());
        assertEquals(new SingleBlock(LexicalStructure.structure("a+b;")), block.children.get(0));

        BracesBlock multiBlock = (BracesBlock)block.children.get(1);
        assertEquals(1, multiBlock.children.size());
        assertEquals(new SingleBlock(LexicalStructure.structure("c+d;")), multiBlock.children.get(0));


        assertEquals(new SingleBlock(LexicalStructure.structure("e+f;")), block.children.get(2));
    }

}
