package jp.co.scriptjava;

import java.util.List;

import jp.co.scriptjava.statement.Statement;

public class ScriptJava {

    private List<List<Statement>> statementList;

    private List<CommentMap> commentMaps;

    public ScriptJava(List<List<Statement>> statementList, List<CommentMap> commentMaps) {
        
        this.statementList = statementList;
        this.commentMaps = commentMaps;
    }
    
}
