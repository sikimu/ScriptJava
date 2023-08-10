package jp.co.scriptjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.block.SingleBlock;
import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;
import jp.co.scriptjava.statement.MethodStatement;

public class MethodExecutorTest {
    @Test
    void intを返すstaticメソッドの実行テスト(){
        String source = "public static int method1() {"
                + "    return 1;"
                + "}";
        List<Lexical> lexicals = LexicalStructure.structure(source);
        MultiBlock block = BlockStructure.structure(lexicals);
        SingleBlock definitionBlock = (SingleBlock)block.children.get(0);
        MultiBlock lexicalBlock = (MultiBlock)block.children.get(1);

        MethodStatement statement = new MethodStatement(definitionBlock, lexicalBlock);

        assertEquals(1, MethodExecutor.call(statement));
    }
}
