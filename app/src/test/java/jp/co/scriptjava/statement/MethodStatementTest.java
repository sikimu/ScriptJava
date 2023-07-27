package jp.co.scriptjava.statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.LexicalMultiBlock;
import jp.co.scriptjava.block.LexicalSingleBlock;
import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;

public class MethodStatementTest {
    
    @Test
    public void 処理文のみのメソッド() {
        String source = "public void method1() {"
                + "    System.out.println(\"Hello World!\");"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        LexicalMultiBlock block = BlockStructure.structure(lexicals);
        LexicalSingleBlock definitionBlock = (LexicalSingleBlock)block.children.get(0);
        LexicalMultiBlock lexicalBlock = (LexicalMultiBlock)block.children.get(1);

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
        LexicalMultiBlock block = BlockStructure.structure(lexicals);
        LexicalSingleBlock definitionBlock = (LexicalSingleBlock)block.children.get(0);
        LexicalMultiBlock lexicalBlock = (LexicalMultiBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertTrue(statement.compound.statementList.get(0) instanceof WhileStatement);
    }
}
