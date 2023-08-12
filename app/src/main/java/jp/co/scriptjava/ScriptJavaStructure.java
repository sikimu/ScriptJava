package jp.co.scriptjava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.block.BlockStructure;
import jp.co.scriptjava.block.BracesBlock;
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
        
        List<SjSource> sjSourceList = new ArrayList<SjSource>();

        for (File file : list) {
            
            String str = FileUtil.readFileToString(file);

            // 字句リストに変換する
            List<Lexical> lexicals = LexicalStructure.structure(str);
            BracesBlock block = BlockStructure.structure(lexicals);
            
            List<Statement> statements = StatementStructure.structure(block);
            CommentMap commentMap = new CommentMap(lexicals);

            sjSourceList.add(new SjSource(statements, commentMap));
        }

        return new ScriptJava(sjSourceList);
    }
}
