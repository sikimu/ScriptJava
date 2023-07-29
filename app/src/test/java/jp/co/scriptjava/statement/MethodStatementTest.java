package jp.co.scriptjava.statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.MultiBlock;
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
        MultiBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        MultiBlock lexicalBlock = (MultiBlock)block.children.get(1);

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
        MultiBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        MultiBlock lexicalBlock = (MultiBlock)block.children.get(1);

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
        MultiBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        MultiBlock lexicalBlock = (MultiBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof ForStatement);
    }
}
