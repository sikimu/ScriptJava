package jp.co.scriptjava.lexical;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @Test
    void 文字列が含まれている() {

    }

    @ParameterizedTest
    @MethodSource("文字列が含まれているパラメータ")
    void 文字や文字列が含まれている(String source, String expected) {

        List<Lexical> list = LexicalStructure.structure(source);
        assertEquals(expected, list.get(1).toString());
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
    void 文字が含まれている() {
        String source = "abc'def'ghi'jkl\\'mno'pqr";
        List<Lexical> list = LexicalStructure.structure(source);

        assertEquals("abc", list.get(0).toString());
        assertEquals("'def'", list.get(1).toString());
        assertEquals("ghi", list.get(2).toString());
        assertEquals("'jkl\\'mno'", list.get(3).toString());
        assertEquals("pqr", list.get(4).toString());

    }
}
