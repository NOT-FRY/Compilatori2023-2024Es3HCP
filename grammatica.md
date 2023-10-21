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

Expr
-> Expr Relop Expr
| ID
| INUMBER
| FNUMBER

}

### First e Follow

|          | First                                      | Follow                                                                    |
|----------|--------------------------------------------|---------------------------------------------------------------------------|
| Relop    | LE, LT, GT, GE, EQ, NE, ADD, MIN, MUL, DIV | $                                                                         |
| S        | IF, ID, WHILE                              | EOF, $                                                                    |
| Program  | IF, ID, WHILE                              | EOF, $                                                                    |
| Program' | SEMI, ε                                    | EOF, $                                                                    |
| Stmt     | IF, ID, WHILE                              | EOF, SEMI, RCUR, IF, $                                                    |
| Stmt'    | LPAR                                       | EOF, ELSE, $                                                              |
| Expr     | ID, INUMBER, FNUMBER                       | EOF, LE, LT, GT, GE, EQ, NE, ADD, MIN, MUL, DIV,RPAR, SEMI, RCUR, LPAR, $ |


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





