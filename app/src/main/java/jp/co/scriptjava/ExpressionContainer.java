package jp.co.scriptjava;

import java.util.List;

import jp.co.scriptjava.lexical.Lexical;

public class ExpressionContainer {

    public final int value;

    public ExpressionContainer(List<Lexical> lexicals) {

        Lexical lexical = lexicals.get(0);

        value = Integer.valueOf(lexical.value);
    }
}
