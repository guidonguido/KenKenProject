# KenKenProject
KenKen game GUI and resolutor

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

