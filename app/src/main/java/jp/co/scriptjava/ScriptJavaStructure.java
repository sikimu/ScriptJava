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

        // 字句を構造的にブロック化したものを作る
        List<MultiBlock> lexicalBlockList = new ArrayList<MultiBlock>();
        for (List<Lexical> lexicals : lexicalList) {
            lexicalBlockList.add(BlockStructure.structure(lexicals));
        }

        // ステートメントリストに変換する
        List<List<Statement>> statementList = new ArrayList<List<Statement>>();
        for (MultiBlock block : lexicalBlockList) {
            statementList.add(StatementStructure.structure(block));
        }

        return new ScriptJava(statementList, commentMaps);
    }
}