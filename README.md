# KenKenProject
KenKen game GUI and resolutor.

Il KenKen è un gioco di logica, ispirato al Sudoku che consiste in una griglia di dimensioni variabili (i più comuni vanno dalle griglie 3x3 fino alle più difficili 6x6), nelle quali bisogna disporre le cifre da 1 a n senza che ci siano ripetizioni né nelle righe né nelle colonne (come per il Sudoku). La griglia inizialmente è totalmente vuota, e divisa in blocchi di diverse forme da linee più spesse; in ogni blocco viene riportato un numero, seguito da un operatore aritmetico (+, -, x o ÷), che indica l'operazione da effettuare tra le varie cifre del blocco. 
La griglia va completata in modo che, effettuando l'operazione riportata in ciascun blocco tra le sue
cifre, si ottenga esattamente il risultato richiesto (sempre un numero intero positivo). Le cifre si possono ripetere all'interno dei blocchi, a condizione però che non si trovino sulla stessa riga o colonna.

**STRUTTURA DEL GIOCO**
La classe Gioco implementa i metodi necessari allo svolgimento dell’algoritmo di risoluzione; il metodo template risolvi della classe Problema implementa l’algoritmo risolutivo, ma lascia il compito dell’implementazione dei singoli passi alla sottoclasse.
La griglia di gioco è implementata come una matrice di Cell, in quanto ogni cella di una griglia KenKen deve mantenere le informazioni relative alla gabbia di appartenenza, oltre alla sua posizione e il numero contenuto.
L’algoritmo risolutivo parte in ogni caso la primoPuntoDiScelta che è la cella in posizione (0,0) della griglia, arrivando ad una soluzione quando si è riempita la matrice fino all’ ultimoPuntoDiScelta, ovvero la cella in basso a destra della griglia.
La scelta di utilizzare dei punti di scelta fissi, non tenendo in considerazione la copertura della griglia (parziale) da parte di un Client del gioco, è giustificata dal fatto che un puzzle KenKen ben costruita come quella visibile nel documento di specifica o qualunque altra trovabile online ha una sola soluzione; maggiori dettagli in Appendice.
Secondo le regole del KenKen un numero è assegnabile ad una determinata cella se non è già presente nella relativa riga e colonna (validNumber) e se il suo inserimento non compromette il rispetto del vincolo della griglia in cui si trova (validCage). 

**DESIGN PATTERN UTILIZZATI**
*Template Method*
La classe astratta Problema<P,S> implementa una versione iterativa della tecnica backtracking 
nel metodo template risolvi().
Tutti i metodi concreti necessari alla risoluzione di uno specifico problema, in questo caso del gioco KenKen, sono implementati nella classe concreta Gioco.

*Singleton*
La classe GiocoObservable è pensata come punto di contatto fra le viste ed il modello, ovvero 
il Gioco.
Non è gradita l’esistenza di più istanze di un risolutore o di più istanze di Gioco. Qualunque sia la vista, può accedere alla griglia dell’istanza di gioco attuale utilizzando l’unica istanza esistente del Singleton.
Ogni modifica all’attributo gioco avrà conseguenze su tutte le viste.

*Observer*
Il pattern Observer permette di realizzare più viste dello stato del Gioco, in particolare dei numeri 
presenti nella sua griglia.
Tuttavia, è evidente che non è la dipendenza uno-a-molti fra il gioco e le eventuali viste ad essere il motivo principale per cui si è deciso di utilizzare il pattern. Il vantaggio principale tratto dall’uso del pattern Observer è il meccanismo di notifica.
È ampiamente sfruttata l’interazione publish-subscribe: ogni qual volta il gioco cambia stato, viene notificato agli osservatori che seguendo un pull model agiscono di conseguenza.
Si noti che il pattern originale è modificato, in quanto GameActionController non mantiene un riferimento al Gioco e non è nemmeno interessato allo stato della griglia, ma solo se essa è stata modificata almeno una volta oppure no.

*Composite*
Si è pensato al Panel principale che rappresenta la grafica della griglia come ad un contenitore di altri elementi grafici, come altri Panel o Scritte.
L’unico oggetto ad essere composto da altre componenti è il Panel, mentre NumberLable e TargetLable sono delle foglie che non posso essere ulteriormente composti.
Dall’uso che il GamePanel (client) fa dei PanelComponent è deducibile la semplicità di progettazione che il pattern introduce.

*Command*
Le modifiche del modello, ovvero del gioco, non sono eseguite direttamente dai Controller che compongono la vista ma sono incapsulate ed inoltrate ad un CommandHandler sotto forma di Command.
Gli oggetti di tipo Command contengono le operazioni necessarie all’esecuzione di un particolare comando e sono gli unici oggetti tramite cui la vista può apportare modifiche al modello.
Il ciclo di vita di un comando, però, non è lineare; i controller inviano i comandi ad un HistoryCommandHandler che memorizza uno storico dei comandi e volge la funzione di Invoker. Prima di chiedere al comando di portare a termine la richiesta, salva in uno storico la richiesta di esecuzione del comando in modo da permettere operazioni di Undo e Redo.

**CARICAMENTO DI UN NUOVO FILE .KEN**

Tramite la GUI è possibile avviare una nuova partita caricando un file .ken che rispetti le specifiche per la creazione di un puzzle KenKen.

Un file KEN è ben costruito se rispetta le seguenti regole:
•	La prima riga contiene il numero di gabbie presenti nella griglia
•	Le successive righe contengono le informazioni sulle gabbie nel seguente ordine:
  1.	Numero target
  2.	Operazione da eseguire sulle celle
  3.	Numero di celle presenti nella gabbia
  4.	Posizione delle celle nel formato (nRiga,nColonna) separate da uno spazio
  
  
  
**PERCHè NON è POSSIBILE NAVIGARE LE SOLUZIONI**

Un puzzle KenKen ben costruito ha un’unica soluzione. La griglia di esempio presente nel documento di specifica è un puzzle ben costruito, così come tutti quelli presenti online o nei libri che raccolgono giochi del genere.
L’unico modo per trovare un puzzle non ben costruito che non rispetti le specifiche generali sull’unicità delle soluzioni del KenKen, sarebbe costruirselo da soli sfruttando la propria inesperienza nel campo della creazione di puzzle KenKen.

Seppur la GUI non permetta di navigare le soluzioni, l’esecuzione del metodo risolvi applicato ad una particolare griglia ne tiene conto! 
Per averne la prova basta generare un file .ken che rappresenti un puzzle con soluzioni multiple, caricarlo e risolverlo tramite interfaccia grafica ed analizzare l’output del terminale: saranno elencate tutte le soluzioni. 

