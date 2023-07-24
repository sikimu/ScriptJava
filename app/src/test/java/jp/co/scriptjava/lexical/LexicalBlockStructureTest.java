package jp.co.scriptjava.lexical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LexicalBlockStructureTest {

    @Test
    void 括弧なし基本パターン() {
        List<Lexical> lexicals = LexicalStructure.structure("a+b;c+d;");   
        
        LexicalRootBlock block = LexicalBlockStructure.structure(lexicals);
        assertEquals(2, block.children.size());
        assertEquals(new LexicalSingleBlock(LexicalStructure.structure("a+b;")), block.children.get(0));
        assertEquals(new LexicalSingleBlock(LexicalStructure.structure("c+d;")), block.children.get(1));
    }

    @Test
    void 括弧あり基本パターン() {
        List<Lexical> lexicals = LexicalStructure.structure("a+b;{c+d;}e+f;");   
        
        LexicalRootBlock block = LexicalBlockStructure.structure(lexicals);
        assertEquals(3, block.children.size());
        assertEquals(new LexicalSingleBlock(LexicalStructure.structure("a+b;")), block.children.get(0));

        LexicalMultiBlock multiBlock = (LexicalMultiBlock)block.children.get(1);
        assertEquals(1, multiBlock.children.size());
        assertEquals(new LexicalSingleBlock(LexicalStructure.structure("c+d;")), multiBlock.children.get(0));


        assertEquals(new LexicalSingleBlock(LexicalStructure.structure("e+f;")), block.children.get(2));
    }

}
