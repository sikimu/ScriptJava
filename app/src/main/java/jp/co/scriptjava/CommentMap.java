package jp.co.scriptjava;

import java.util.HashMap;
import java.util.List;

import jp.co.scriptjava.lexical.Lexical;

public class CommentMap {
    
    /** 直前のコメント */
    private HashMap<Lexical, Lexical> beforeMap = new HashMap<Lexical, Lexical>();

    /** 直後のコメント */
    private HashMap<Lexical, Lexical> afterMap = new HashMap<Lexical, Lexical>();

    public CommentMap(List<Lexical> lexicalList) {

        for(int i = 0; i < lexicalList.size(); i++) {
            Lexical lexical = lexicalList.get(i);
            if(lexical.type == Lexical.TYPE.COMMENT) {
                if(i > 0) {
                    beforeMap.put(lexicalList.get(i - 1), lexical);
                }
                if(i < lexicalList.size() - 1) {
                    afterMap.put(lexicalList.get(i + 1), lexical);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "CommentMap [beforeMap=" + beforeMap + ", afterMap=" + afterMap + "]";
    }
}
