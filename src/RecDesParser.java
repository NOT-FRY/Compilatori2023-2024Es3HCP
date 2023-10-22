import java.io.EOFException;

public class RecDesParser {
    static int ptr;

    private Lexer lexer;
    private Token currentToken;

    public RecDesParser(String inputFile){
        lexer = new Lexer();
        lexer.initialize(inputFile);
        currentToken = null;
    }

    public boolean S ()throws Exception{ //S-> Program  EOF
        if(!Program()) {
            return false;
        }
        else {
            return currentToken.getName().equals("EOF");
        }

    }

    public boolean Program()throws Exception{ // Program-> Stmt Program'
        if(!Stmt()){
            return false;
        }else{
            return Program1();
        }
    }

    /*
    *
    *
      Stmt -> IF LPAR Expr RPAR LCUR Stmt RCUR Stmt'
            | ID ASS Expr
            | WHILE LPAR Expr RPAR LCUR Stmt RCUR
    *
    * */

    public boolean Stmt() throws Exception {
        currentToken = lexer.next_token();

        if(currentToken.getName().equals("IF")){
            currentToken = lexer.next_token();
            if(currentToken.getName().equals("LPAR")){
                if(!Expr()) {
                    return false;
                }else{
                    currentToken = lexer.next_token();
                    if(currentToken.getName().equals("RPAR")) {
                        currentToken = lexer.next_token();
                        if(currentToken.getName().equals("LPAR")) {

                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }
            }else{
                return false;
            }

        }
    }

    public boolean Program1(){

    }

    public boolean Expr(){

    }

}


