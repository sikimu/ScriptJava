package jp.co.scriptjava;

import java.util.List;

import jp.co.scriptjava.statement.ClassStatement;
import jp.co.scriptjava.statement.ImportStatement;
import jp.co.scriptjava.statement.PackageStatement;
import jp.co.scriptjava.statement.Statement;

public class SjSource {

    /**
     * コメントマップ
     */
    final public CommentMap commentMap;

    /**
     * パッケージ
     */
    final public Statement packageStatement;

    /**
     * インポートリスト
     */
    final public List<Statement> importStatementList;

    /**
     * クラスリスト
     */
    final public List<Statement> classStatement;

    public SjSource(List<Statement> statements, CommentMap commentMap) {
        this.commentMap = commentMap;
        this.packageStatement = statements.stream().filter(s -> s instanceof PackageStatement).findFirst().orElse(null);
        this.importStatementList = statements.stream().filter(s -> s instanceof ImportStatement).toList();
        this.classStatement = statements.stream().filter(s -> s instanceof ClassStatement).toList();
    }
    
}
