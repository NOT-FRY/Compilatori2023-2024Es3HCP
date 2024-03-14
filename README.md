## Correzione grammatica 

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
-> IF Expr THEN Stmt END IF
| IF Expr THEN Stmt ELSE Stmt END IF
| ID ASSIGN Expr
| WHILE Expr LOOP Stmt END LOOP

È stata eliminata applicando le regole per la rimozione di una fattorizzazione sinistra.

Il risultato è:

Stmt
-> IF Expr THEN Stmt Stmt'
| ID ASS Expr
| WHILE  Expr LOOP Stmt END LOOP

Stmt'
-> ELSE  Stmt END IF
| END IF

## Correzioni effettuate nei test forniti:
* file_source1 : aggiunti "end if" richiesti dalla grammatica a riga 5 e 10
* file_source2: rimosso ";" prima dell'else e rimosso punto riga 5 per farlo identificare come FNUMBER
* file_source3: rimosso "if" pendente riga 2
* file_source4: inserito "end if" richiesto a riga 3, 9, 16, rimosso ";" riga 19
* file_source5: sostituito "while" con if, aggiunto "end if" righe 3,6 e rimossi ";"
* file_source6: sistemate costanti numeriche righe 1 e 3 per farle riconoscere come FNUMBER, aggiunta assegnazione alle righe 3 e 6, aggiunto "end if" riga 4
* file_source7: rimossi caratteri "." a righe 1 e 22 per far identificare costanti come FNUMBER, inseriti "end if" righe 4, 8, 16, 25, 29, 37, rimosso ";" riga 41
* file_source8: inserito "end if" riga 5