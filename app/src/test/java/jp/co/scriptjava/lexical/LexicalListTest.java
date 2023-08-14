package jp.co.scriptjava.lexical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LexicalListTest {
    @Test
    void 識別子のみ() {
        String source = "abc";
        LexicalList list = new LexicalList(source);
        
        assertEquals("abc", list.get(0));
    }

    @Test
    void 識別子の前後に空白() {
        String source = "  abc  ";
        LexicalList list = new LexicalList(source);
        
        assertEquals("abc", list.get(0));
    }

    @Test
    void 識別子が2つ() {
        String source = "abc def";
        LexicalList list = new LexicalList(source);
        
        assertEquals("abc", list.get(0));
        assertEquals("def", list.get(1));
    }

    @Test
    void 区切り文字が含まれる() {
        String source = "abc{def}";
        LexicalList list = new LexicalList(source);
        
        assertEquals("abc", list.get(0));
        assertEquals("{", list.get(1));
        assertEquals("def", list.get(2));
        assertEquals("}", list.get(3));
    }

    @Test
    void 区切り文字の優先順位(){
        String source = "abc...def.";
        LexicalList list = new LexicalList(source);

        assertEquals("abc", list.get(0));
        assertEquals("...", list.get(1));
        assertEquals("def", list.get(2));
        assertEquals(".", list.get(3));
    }

    @ParameterizedTest
    @MethodSource("文字列が含まれているパラメータ")
    void 文字や文字列が含まれている(String source, String expected) {

        LexicalList list = new LexicalList(source);
        assertEquals(expected, list.get(1));
    }
    static Stream<Arguments> 文字列が含まれているパラメータ() {
        return Stream.of(
                Arguments.of("aaa\"a\"bbb", "\"a\""),
                Arguments.of("aaa \"a\" bbb", "\"a\""),
                Arguments.of("aaa \"a\\\"a\" bbb", "\"a\\\"a\""),
                Arguments.of("aaa \"a\\\\a\" bbb", "\"a\\\\a\""),
                Arguments.of("aaa \"\" bbb", "\"\""),
                Arguments.of("aaa \"\\\\\" bbb", "\"\\\\\""),

                Arguments.of("aaa'a'bbb", "'a'"),
                Arguments.of("aaa 'a' bbb", "'a'"),
                Arguments.of("aaa '\\'' bbb", "'\\''"),
                Arguments.of("aaa '\\\\' bbb", "'\\\\'")
            );
    }

    @Test
    void コメントが含まれている最後まで() {
        String source = "aaa // bbb";
        LexicalList list = new LexicalList(source);
        assertEquals("// bbb", list.get(1));
    }

    @Test
    void コメントが含まれている改行まで() {
        String source = "aaa // bbb\nccc";
        LexicalList list = new LexicalList(source);
        assertEquals("// bbb", list.get(1));
    }    

    @Test
    void 複数行コメントが含まれている最後まで() {
        String source = "aaa /* bbb */";
        LexicalList list = new LexicalList(source);
        assertEquals("/* bbb */", list.get(1));
    }

    @Test
    void 複数行コメントが含まれている改行こみ() {
        String source = "aaa /* bbb\nccc */ccc";
        LexicalList list = new LexicalList(source);
        assertEquals("/* bbb\nccc */", list.get(1));
    }
}
