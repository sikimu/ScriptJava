package jp.co.scriptjava.lexical;

/**
 * 識別子
 */
public class Identifier implements Lexical{
    
    final String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}