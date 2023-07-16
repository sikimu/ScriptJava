package jp.co.scriptjava.lexical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LexicalStructureTest {
    @Test
    void 識別子のみ() {
        String source = "abc";
        List<Lexical> list = LexicalStructure.structure(source);
        
        assertEquals("abc", list.get(0).toString());
    }

    @Test
    void 識別子の前後に空白() {
        String source = "  abc  ";
        List<Lexical> list = LexicalStructure.structure(source);
        
        assertEquals("abc", list.get(0).toString());
    }

    @Test
    void 識別子が2つ() {
        String source = "abc def";
        List<Lexical> list = LexicalStructure.structure(source);
        
        assertEquals("abc", list.get(0).toString());
        assertEquals("def", list.get(1).toString());
    }

    @Test
    void 区切り文字が含まれる() {
        String source = "abc{def}";
        List<Lexical> list = LexicalStructure.structure(source);
        
        assertEquals("abc", list.get(0).toString());
        assertEquals("{", list.get(1).toString());
        assertEquals("def", list.get(2).toString());
        assertEquals("}", list.get(3).toString());
    }

    @Test
    void 区切り文字の優先順位(){
        String source = "abc...def.";
        List<Lexical> list = LexicalStructure.structure(source);

        assertEquals("abc", list.get(0).toString());
        assertEquals("...", list.get(1).toString());
        assertEquals("def", list.get(2).toString());
        assertEquals(".", list.get(3).toString());
    }
}
