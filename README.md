La grammatica inizalmente presenta due riscorsioni a sinistra:

1) Program
-> Program SEMI Stmt
| Stmt

È stata eliminata applicando le regole per la rimozione di una ricorsione sinistra.

Il risultato è:

Program
-> Stmt Program'

Program'
->  SEMI Stmt Program' | ε 



2) Expr
-> Expr Relop Expr
| ID
| INUMBER
| FNUMBER
   
Il risultato è:

Expr -> ID Expr' | INUMBER Expr' | FNUMBER Expr'

Expr' -> Relop Expr Expr' | ε



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



Dopo vari tentativi, si è notata un' ambiguità residua nella grammatica, dovuta al problema del "dangling else"
e si è deciso di eliminarla utilizzando il seguente costrutto:

Stmt
->
MatchedStmt | OpenStmt

MatchedStmt
->
IF Expr THEN MatchedStmt ELSE MatchedStmt
| ID ASS Expr
| WHILE  Expr LOOP MatchedStmt END LOOP

OpenStmt->
IF Expr THEN OpenStmt'

OpenStmt'
-> Stmt
| MatchedStmt ELSE OpenStmt



Dopo vari tentativi, si è notata un' ambiguità residua nella grammatica, dovuta al problema del "dangling else"
e si è deciso di eliminarla utilizzando il seguente costrutto:

Stmt
->
MatchedStmt | OpenStmt

MatchedStmt
->
IF Expr THEN MatchedStmt ELSE MatchedStmt
| ID ASS Expr
| WHILE  Expr LOOP MatchedStmt END LOOP

OpenStmt->
IF Expr THEN OpenStmt'

OpenStmt'
-> Stmt
| MatchedStmt ELSE OpenStmt


