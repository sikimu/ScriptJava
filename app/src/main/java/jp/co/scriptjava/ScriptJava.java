package jp.co.scriptjava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;
import jp.co.scriptjava.statement.Statement;
import jp.co.scriptjava.statement.StatementStructure;

public class ScriptJava {

    public ScriptJava(String folderPath) {
        try {
            // フォルダ内の全Javaファイルを取得する
            List<File> list = FileUtil.getAllFiles(folderPath).stream()
                    .filter(file -> file.getName().endsWith(".java"))
                    .toList();
            
            // ファイルの内容をリストに格納する
            List<String> sourceList = new ArrayList<String>();
            for (File file : list) {
                sourceList.add(FileUtil.readFileToString(file));
            }

            // 字句リストに変換する
            List<List<Lexical>> lexicalList = new ArrayList<List<Lexical>>();
            for (String source : sourceList) {
                lexicalList.add(LexicalStructure.structure(source));
            }

            // コメントマップを作成する
            List<CommentMap> commentMaps = new ArrayList<CommentMap>();
            for (List<Lexical> lexicals : lexicalList) {
                commentMaps.add(new CommentMap(lexicals));
            }

            // ステートメントリストに変換する
            List<List<Statement>> statementList = new ArrayList<List<Statement>>();
            for (List<Lexical> lexicals : lexicalList) {
                statementList.add(StatementStructure.structure(lexicals));
            }

            // ステートメントを出力する
            for (List<Statement> statements : statementList) {
                for (Statement statement : statements) {
                    System.out.println(statement);
                }
            }

            // コメントマップを出力する
            for (CommentMap commentMap : commentMaps) {
                System.out.println(commentMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
