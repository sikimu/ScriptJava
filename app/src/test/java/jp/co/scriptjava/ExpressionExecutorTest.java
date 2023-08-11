package jp.co.scriptjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;

public class ExpressionExecutorTest {

    @Test
    void intを返す(){
        
        List<Lexical> lexicals = LexicalStructure.structure("1");

        int result = ExpressionExecutor.execute(new ExpressionContainer(lexicals));

        assertEquals(1, result);
        
    }
}
