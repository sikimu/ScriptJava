package jp.co.scriptjava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.MultiBlock;
import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;
import jp.co.scriptjava.statement.Statement;
import jp.co.scriptjava.statement.StatementStructure;

public class ScriptJavaStructure {

    public static ScriptJava structure(String folderPath) throws Exception {

        // フォルダ内の全Javaファイルを取得する
        List<File> list = FileUtil.getAllFiles(folderPath).stream()
                .filter(file -> file.getName().endsWith(".java"))
                .toList();
        
        List<CommentMap> commentMaps = new ArrayList<CommentMap>();
        List<List<Statement>> statementList = new ArrayList<List<Statement>>();

        for (File file : list) {
            String str = FileUtil.readFileToString(file);
            // 字句リストに変換する
            List<Lexical> lexicals = LexicalStructure.structure(str);

            commentMaps.add(new CommentMap(lexicals));

            MultiBlock block = BlockStructure.structure(lexicals);
            statementList.add(StatementStructure.structure(block));
        }

        return new ScriptJava(statementList, commentMaps);
    }
}
