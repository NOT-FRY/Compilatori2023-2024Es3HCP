import java.io.File;

public class Tester {
    public static void main(String[] args) throws Exception{
        if (args.length == 0) {
            System.out.println("Argomenti insufficienti\n Inserire il nome del file come parametro");
            return;
        }

        String filePath = "test_files" + File.separator +args[0];
        RecDesParser parser = new RecDesParser(filePath);
        boolean isValidInput = parser.S();
        System.out.println("Esecuzione del parser sul file "+filePath);
        if(isValidInput){
            System.out.println("Sequenza valida.");
        }else{
            System.out.println("Sequenza non valida.");
        }

    }
}
