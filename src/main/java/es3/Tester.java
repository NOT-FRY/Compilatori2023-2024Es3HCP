package es3;

import java.io.File;

public class Tester {
    public static void main(String[] args) throws Exception{
        if (args.length == 0) {
            System.out.println("Argomenti insufficienti\n Inserire il nome del file come parametro");
            System.exit(1);
        }

        //String filePath = "test_files" + File.separator +args[0];
        RecDesParser parser = new RecDesParser(args[0]);
        boolean isValidInput = parser.S();
        //System.out.println("Esecuzione del parser sul file "+args[0]);
        if(isValidInput){
            System.out.println("Input is valid");
        }else{
            System.out.println("Syntax error");
        }

    }
}
