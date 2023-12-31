package jp.co.scriptjava.statement;

import jp.co.scriptjava.block.SingleBlock;

public class ImportStatement extends Statement {

    final public String packageName;

    public ImportStatement(SingleBlock lexicalSingleBlock) {

        //import文の構文チェック
        if (lexicalSingleBlock.get(0).value.equals("import") == false) {
            throw new RuntimeException("import文の構文が不正です。");
        }

        packageName = lexicalSingleBlock.subList(1, lexicalSingleBlock.size() - 1).stream().map(lexical -> lexical.value).reduce((a, b) -> a + b).get();
    }
    
    @Override
    public String toString() {
        return "import " + packageName + ";";
    }
}
