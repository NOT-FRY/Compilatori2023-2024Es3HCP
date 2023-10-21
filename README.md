La grammatica inizalmente presenta una riscorsione sinistra:

Program
-> Program SEMI Stmt
| Stmt

È stata eliminata applicando le regole per la rimozione di una ricorsione sinistra.

Il risultato è:

Program
-> Stmt Program'

Program'
->  SEMI Stmt Program' | ε 


La grammatica inizalmente presenta una fattorizzazione sinistra:

Stmt
-> IF LPAR Expr RPAR LCUR Stmt RCUR
| IF LPAR Expr RPAR LCUR Stmt RCUR ELSE LCUR Stmt RCUR
| ID ASS Expr
| WHILE LPAR Expr RPAR LCUR Stmt RCUR

È stata eliminata applicando le regole per la rimozione di una fattorizzazione sinistra.

Il risultato è:

Stmt
-> IF LPAR Expr RPAR LCUR Stmt RCUR Stmt'
| ID ASS Expr
| WHILE LPAR Expr RPAR LCUR Stmt RCUR

Stmt'
-> ELSE LCUR Stmt RCUR Stmt'
| ε


