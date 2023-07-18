package jp.co.scriptjava.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.lexical.Lexical;

public class StatementStructureTest {
    @Test
    void 識別子とセミコロン() {
        List<Lexical> lexicalList = new ArrayList<Lexical>();
        lexicalList.add(new Lexical(Lexical.TYPE.IDENTIFIER, "a"));
        lexicalList.add(new Lexical(Lexical.TYPE.SEPARATOR, ";"));
        lexicalList.add(new Lexical(Lexical.TYPE.IDENTIFIER, "b"));
        
        List<Statement> statementList = StatementStructure.structure(lexicalList);

        assertEquals("a;", statementList.get(0).toString());
    }
}
