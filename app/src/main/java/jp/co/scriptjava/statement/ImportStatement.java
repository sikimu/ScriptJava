package jp.co.scriptjava.statement;

import java.util.List;

import jp.co.scriptjava.lexical.Lexical;

public class ImportStatement extends Statement {

    final public String packageName;

    public ImportStatement(List<Lexical> lexicalList) {

        //import文の構文チェック
        if (lexicalList.get(0).value.equals("import") == false) {
            throw new RuntimeException("import文の構文が不正です。");
        }

        packageName = lexicalList.subList(1, lexicalList.size() - 1).stream().map(lexical -> lexical.value).reduce((a, b) -> a + b).get();
    }
    
    @Override
    public String toString() {
        return "import " + packageName + ";";
    }
}
