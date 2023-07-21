package jp.co.scriptjava.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalBlock;
import jp.co.scriptjava.lexical.LexicalRootBlock;

public class StatementStructureTest {
    @Test
    void 識別子とセミコロン() {
        List<Lexical> lexicalList = new ArrayList<Lexical>();
        lexicalList.add(new Lexical(Lexical.TYPE.IDENTIFIER, "a"));
        lexicalList.add(new Lexical(Lexical.TYPE.SEPARATOR, ";"));

        LexicalRootBlock block = new LexicalRootBlock(List.of(new LexicalBlock(lexicalList, new ArrayList<>())));
        
        List<Statement> statementList = StatementStructure.structure(block);

        assertEquals("a;", statementList.get(0).toString());
    }
}
