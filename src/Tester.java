import java.io.File;

public class Tester {
    public static void main(String[] args) throws Exception{

        String filePath = "test_files" + File.separator +"file_source0.txt";
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
