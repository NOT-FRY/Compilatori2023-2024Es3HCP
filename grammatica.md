# Grammatica

N = {S, Program, Stmt, Expr, Relop},

T = {EOF, SEMI, COM, IF, ELSE, ID, ASS, WHILE, LOOP, INUMBER, FNUMBER, RPAR, LPAR, RCUR, LCUR},  

S

P = {

Relop
-> LE | LT |GT | GE | EQ | NE | ADD | MIN | MUL | DIV

S
-> Program  EOF

Program
-> Stmt Program'

Program'
->  SEMI Stmt Program' | ε

Stmt
-> IF LPAR Expr RPAR LCUR Stmt RCUR Stmt'
| ID ASS Expr
| WHILE LPAR Expr RPAR LCUR Stmt RCUR

Stmt'
-> ELSE LCUR Stmt RCUR Stmt'
| ε

Expr -> ID Expr' | INUMBER Expr' | FNUMBER Expr'

Expr' -> Relop Expr Expr' | ε

}

### First e Follow

|          | First                                          | Follow                                                            |
|----------|------------------------------------------------|-------------------------------------------------------------------|
| Relop    | LE, LT, GT, GE, EQ, NE, ADD, MIN, MUL, DIV     | ID, INUMBER, FNUMBER                                                                 |
| S        | IF, ID, WHILE                                  | $                                                                 |
| Program  | IF, ID, WHILE                                  | EOF                                                               |
| Program' | SEMI, ε                                        | EOF                                                               |
| Stmt     | IF, ID, WHILE                                  | SEMI, EOF, RCUR                                                   |
| Stmt'    | ELSE, ε                                        | SEMI, EOF, RCUR                                                   |
| Expr     | ID, INUMBER, FNUMBER                           | RPAR, SEMI, EOF, RCUR, LE, LT, GT, GE, EQ, NE, ADD, MIN, MUL, DIV |
| Expr'    | LE, LT, GT, GE, EQ, NE, ADD, MIN, MUL, DIV , ε | RPAR, SEMI, EOF, RCUR, LE, LT, GT, GE, EQ, NE, ADD, MIN, MUL, DIV |

### Parsing table 
| Lessema | Nome del token | Attributo |
|---------|----------------|-----------|
| <--     | *ASS*          | -         |
| <       | *LT*           | -         |
| <=      | *LE*           | -         |
| \>      | *GT*           | -         |
| \>=     | *GE*           | -         |
| =       | *EQ*           | -         |
| !=      | *NE*           | -         |
| +       | *ADD*          | -         |
| -       | *MIN*          | -         |
| *       | *MUL*          | -         |
| /       | *DIV*          | -         |





