package jp.co.scriptjava;

public class App {

    public static void main(String[] args) {

        try{
            ScriptJava script = ScriptJavaStructure.structure("c:\\Users\\iihit\\java\\ScriptJava");

            script.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
