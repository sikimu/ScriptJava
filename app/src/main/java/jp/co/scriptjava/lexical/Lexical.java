package jp.co.scriptjava.lexical;

public class Lexical {

    public enum TYPE {
        IDENTIFIER,
        SEPARATOR,
        OPERATOR,
        COMMENT,
    }

    public final TYPE type;

    public final String value;

    public Lexical(TYPE type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Lexical == false){
            return false;
        }
        Lexical lexical = (Lexical)obj;
        if(lexical.type != type){
            return false;
        }
        if(lexical.value.equals(value) == false){
            return false;
        }
        return true;
    }
}
