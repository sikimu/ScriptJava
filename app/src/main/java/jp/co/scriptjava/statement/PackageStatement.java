package jp.co.scriptjava.statement;

import java.util.List;

import jp.co.scriptjava.lexical.Lexical;
import jp.co.scriptjava.lexical.LexicalSingleBlock;

public class PackageStatement extends Statement{

    final public String packageName;

    public PackageStatement(LexicalSingleBlock lexicalSingleBlock) {

        //package文の構文チェック
        if (lexicalSingleBlock.get(0).value.equals("package") == false) {
            throw new RuntimeException("package文の構文が不正です。");
        }

        packageName = lexicalSingleBlock.subList(1, lexicalSingleBlock.size() - 1).stream().map(lexical -> lexical.value).reduce((a, b) -> a + b).get();
    }

    @Override
    public String toString() {
        return "package " + packageName + ";";
    }
}
