package jp.co.scriptjava.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;

public class StatementStructureTest {
    @Test
    void importのテスト() {        
        List<Lexical> lexicals = LexicalStructure.structure("import java.util.List;");
        MultiBlock block = BlockStructure.structure(lexicals);

        List<Statement> statementList = StatementStructure.structure(block);

        assertEquals(1, statementList.size());
        assertTrue(statementList.get(0) instanceof ImportStatement);
    }

    @Test
    void packageのテスト() {        
        List<Lexical> lexicals = LexicalStructure.structure("package jp.co.scriptjava.statement;");
        MultiBlock block = BlockStructure.structure(lexicals);

        List<Statement> statementList = StatementStructure.structure(block);

        assertEquals(1, statementList.size());
        assertTrue(statementList.get(0) instanceof PackageStatement);
    }

    @Test
    void classのテスト(){
        List<Lexical> lexicals = LexicalStructure.structure("public class StatementStructureTest { }");
        MultiBlock block = BlockStructure.structure(lexicals);

        List<Statement> statementList = StatementStructure.structure(block);

        assertEquals(1, statementList.size());
        assertTrue(statementList.get(0) instanceof ClassStatement);
    }
}
