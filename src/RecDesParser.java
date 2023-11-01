import java.io.EOFException;
import java.util.ArrayList;

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
    private Lexer lexer;
    private Token currentToken;
    private int currentTokenIndex;
    private ArrayList<Token> tokenList;

    public RecDesParser(String inputFile) throws Exception{
        lexer = new Lexer();
        lexer.initialize(inputFile);
        tokenList = new ArrayList<Token>();
        Token t = lexer.next_token();
        while(!t.getName().equals("EOF")){
            tokenList.add(t);
            t = lexer.next_token();
        }
        tokenList.add(t);//aggiungo EOF
        currentToken = null;
        currentTokenIndex = 0;
    }

    public boolean S() throws Exception { //S-> Program  EOF
        if (!Program()) {
            return false;
        } else {
            return currentToken.getName().equals("EOF");
        }

    }

    public boolean Program() throws Exception { // Program-> Stmt Program'
        if (!Stmt()) {
            return false;
        } else {
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

        currentToken = tokenList.get(currentTokenIndex++);

        if (currentToken.getName().equals("IF")) {
            if (Expr()) {
                currentToken = tokenList.get(currentTokenIndex++);
                if (currentToken.getName().equals("THEN")) {
                    if (Stmt()) {
                        currentToken = tokenList.get(currentTokenIndex++);
                        if (!currentToken.getName().equals("END"))
                            return false;
                        currentToken = tokenList.get(currentTokenIndex++);
                        if (!currentToken.getName().equals("IF"))
                            return false;
                        return Stmt1();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } else if (currentToken.getName().equals("ID")) {
            currentToken = tokenList.get(currentTokenIndex++);
            if (!currentToken.getName().equals("ASS"))
                return false;
            return Expr();

        } else if (currentToken.getName().equals("WHILE")) {
            if (Expr()) {
                currentToken = tokenList.get(currentTokenIndex++);
                if (!currentToken.getName().equals("LOOP"))
                    return false;
                if (Stmt()) {
                    currentToken = tokenList.get(currentTokenIndex++);
                    if (!currentToken.getName().equals("END"))
                        return false;
                    currentToken = tokenList.get(currentTokenIndex++);
                    if (!currentToken.getName().equals("LOOP"))
                        return false;
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } else
            return false;
    }

    /*
    *
    * Program'->  SEMI Stmt Program' | ε
    * */
    public boolean Program1() throws Exception {
        currentToken = tokenList.get(currentTokenIndex++);
        if (currentToken.getName().equals("SEMI")) {
            if (Stmt()) {
                return Program1();
            } else {
                return false;
            }
        } else { //ε
            currentTokenIndex--;
            return true;
        }
    }

    /*
    *
    * Expr -> ID Expr' | INUMBER Expr' | FNUMBER Expr'
    *
    * */

    public boolean Expr() throws Exception {
        currentToken = tokenList.get(currentTokenIndex++);
        if (currentToken.getName().equals("ID") || currentToken.getName().equals("INUMBER") || currentToken.getName().equals("FNUMBER")) {
            if (Expr1()) {
                return true;
            }
        }
        return false;
    }
    /*
    *
    * Expr' -> Relop Expr Expr' | ε
    * */
    public boolean Expr1() throws Exception {
        currentToken = tokenList.get(currentTokenIndex++);
        if (Relop()) {
            if (Expr()) {
                if (Expr1()) {
                    return true;
                }
            }
        } else {
            currentTokenIndex--;
            return true;
        }
        return false;
    }

    //Relop-> LE | LT |GT | GE | EQ | NE | ADD | MIN | MUL | DIV

    public boolean Relop() {
        if (currentToken.getName().equals("LE") || currentToken.getName().equals("LT")
                || currentToken.getName().equals("GT") || currentToken.getName().equals("GE")
                || currentToken.getName().equals("EQ") || currentToken.getName().equals("NE")
                || currentToken.getName().equals("ADD") || currentToken.getName().equals("MIN")
                || currentToken.getName().equals("MUL") || currentToken.getName().equals("DIV")) {

            return true;

        }
        return false;
    }

    /*
    * Stmt'-> ELSE  Stmt END IF Stmt'| ε
    *
    * */
    public boolean Stmt1() throws Exception {
        currentToken = tokenList.get(currentTokenIndex++);
        if (currentToken.getName().equals("ELSE")) {
            if (Stmt()) {
                currentToken = tokenList.get(currentTokenIndex++);
                if (!currentToken.getName().equals("END"))
                    return false;
                currentToken = tokenList.get(currentTokenIndex++);
                if (!currentToken.getName().equals("IF"))
                    return false;
                return Stmt1();
            } else {
                return false;
            }
        } else { // ε
            currentTokenIndex--;
            return true;
        }
    }

}


