package jp.co.scriptjava.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.block.SingleBlock;
import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;

public class ClassStatementTest {

    @Test
    void 空classのテスト(){
        List<Lexical> lexicals = LexicalStructure.structure("public class StatementStructureTest { }");
        MultiBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        MultiBlock lexicalBlock = (MultiBlock)block.children.get(1);

        ClassStatement statement = new ClassStatement(definitionBlock, lexicalBlock);

        assertEquals("StatementStructureTest", statement.className);
        assertEquals(0, statement.statementList.size());
    }

    @Test
    void メソッド入りのテスト(){
        List<Lexical> lexicals = LexicalStructure.structure("public class StatementStructureTest { public void test(){} }");
        MultiBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        MultiBlock lexicalBlock = (MultiBlock)block.children.get(1);

        ClassStatement statement = new ClassStatement(definitionBlock, lexicalBlock);

        assertEquals("StatementStructureTest", statement.className);
        assertEquals(1, statement.statementList.size());
        assertTrue(statement.statementList.get(0) instanceof MethodStatement);
    }

    @Test
    void enum入りのテスト(){
        List<Lexical> lexicals = LexicalStructure.structure("public class StatementStructureTest { public enum Test{} }");
        MultiBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        MultiBlock lexicalBlock = (MultiBlock)block.children.get(1);

        ClassStatement statement = new ClassStatement(definitionBlock, lexicalBlock);

        assertEquals("StatementStructureTest", statement.className);
        assertEquals(1, statement.statementList.size());
        assertTrue(statement.statementList.get(0) instanceof EnumStatement);
    }
}
