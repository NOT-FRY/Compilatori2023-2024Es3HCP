import java.io.EOFException;
/*
* S
-> Program  EOF

Relop
-> LE | LT |GT | GE | EQ | NE | ADD | MIN | MUL | DIV


Program
-> Stmt Program'

Program'
->  SEMI Stmt Program' | ε

Stmt
-> IF Expr THEN Stmt END IF Stmt'
| ID ASS Expr
| WHILE  Expr LOOP Stmt END LOOP

Stmt'
-> ELSE  Stmt END IF Stmt'
| ε

Expr -> ID Expr' | INUMBER Expr' | FNUMBER Expr'

Expr' -> Relop Expr Expr' | ε
*
* */
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
            if(Expr()) {
                currentToken = lexer.next_token();
                if (currentToken.getName().equals("THEN")) {
                    if(Stmt()){
                        currentToken = lexer.next_token();
                        if(!currentToken.getName().equals("END"))
                            return false;
                        currentToken = lexer.next_token();
                        if(!currentToken.getName().equals("IF"))
                            return false;
                        if(Stmt1()){
                            return true;
                        }
                    }else{
                        return false;
                    }
                } else {
                    return false;
                }
            }else {
                return false;
            }

        }else if(currentToken.getName().equals("ID")){
            currentToken = lexer.next_token();
            if(!currentToken.getName().equals("ASS"))
                return false;
            if(Expr())
                return true;
            else
                return false;
        }else if(currentToken.getName().equals("WHILE")){
            if(Expr()) {
                currentToken = lexer.next_token();
                if(!currentToken.getName().equals("LOOP"))
                    return false;
                if(Stmt()){
                    currentToken = lexer.next_token();
                    if(!currentToken.getName().equals("END"))
                        return false;
                    currentToken = lexer.next_token();
                    if(!currentToken.getName().equals("LOOP"))
                        return false;
                    return true;
                }else{
                    return false;
                }

            }
            else {
                return false;
            }
        }else
            return false;
    }

    public boolean Program1(){

    }

    public boolean Expr(){

    }

}


