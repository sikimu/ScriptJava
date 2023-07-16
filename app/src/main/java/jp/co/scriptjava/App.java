package jp.co.scriptjava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalStructure;

public class App {

    private static String folderPath = "c:\\Users\\iihit\\java\\ScriptJava";

    public static void main(String[] args) {

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
