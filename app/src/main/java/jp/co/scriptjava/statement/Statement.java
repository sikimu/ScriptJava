package jp.co.scriptjava.statement;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.scriptjava.lexical.Lexical;

public class Statement {
    
    List<Lexical> lexicalList;
    
    public Statement(List<Lexical> lexicalList) {
        this.lexicalList = lexicalList;
    }

    @Override
    public String toString() {
        return lexicalList.stream().map(lexical -> lexical.toString()).collect(Collectors.joining());
    }
}
