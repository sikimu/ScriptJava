package jp.co.scriptjava.statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.BracesBlock;
import jp.co.scriptjava.block.SingleBlock;
import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;

public class MethodStatementTest {
    
    @Test
    public void 処理文のみのメソッド() {
        String source = "public void method1() {"
                + "    System.out.println(\"Hello World!\");"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof ProcessStatement);
    }

    @Test
    public void while文のみのメソッド(){
        String source = "public void method1() {"
                + "    while(true){"
                + "        System.out.println(\"Hello World!\");"
                + "    }"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof WhileStatement);
    }

    @Test
    public void for文のみのメソッド(){
        String source = "public void method1() {"
                + "    for(int i = 0; i < 10; i++){"
                + "        System.out.println(\"Hello World!\");"
                + "    }"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof ForStatement);
    }

    @Test
    public void if文のみのメソッド(){
        String source = "public void method1() {"
                + "    if(true){"
                + "        System.out.println(\"Hello World!\");"
                + "    }"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof IfStatement);
    }

    @Test 
    void 戻り値のスローが含まれるテスト(){
        String source = "public void method1() throws Exception {"
                + "    if(true){"
                + "        System.out.println(\"Hello World!\");"
                + "    }"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        //エラーが発生しなければOK
        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertEquals("method1", statement.name);

    }

    @Test
    void try_cathのテスト(){
        String source = "public void method1() {"
                + "    try{"
                + "        System.out.println(\"Hello World!\");"
                + "    }catch(Exception e){"
                + "        System.out.println(\"Hello World!\");"
                + "    }"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof TryStatement);
    }

    @Test
    void try_catch_finalyのテスト(){
        String source = "public void method1() {"
                + "    try{"
                + "        System.out.println(\"Hello World!\");"
                + "    }catch(Exception e){"
                + "        System.out.println(\"Hello World!\");"
                + "    }finally{"
                + "        System.out.println(\"Hello World!\");"
                + "    }"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof TryStatement);
    }

    @Test
    void returnのテスト(){
        String source = "public void method1() {"
                + "    return;"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        BracesBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        BracesBlock lexicalBlock = (BracesBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof ReturnStatement);
    }
}
