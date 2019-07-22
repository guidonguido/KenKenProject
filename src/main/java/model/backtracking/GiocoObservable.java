package model.backtracking;

import sun.plugin.dom.exception.InvalidStateException;

import java.io.*;
import java.util.Observable;
import java.util.StringTokenizer;

public class GiocoObservable extends Observable {

    private static GiocoObservable instance = null;

    private Gioco gioco;

    private GiocoObservable(){}

    public static  synchronized GiocoObservable getInstance()
    {   if(instance == null)
            instance = new GiocoObservable();
        return instance;
    }

    public void newGame(Cell[][] griglia)
    {
        gioco = new Gioco(griglia);
    }

    public void newGame(File file)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int dim = Integer.parseInt(br.readLine());
            Cell[][] griglia = new Cell[dim][dim];

            //leggo le righe contenenti le info sulle gabbie e la posizione delle celle
            for(String line = br.readLine(); line != null; line = br.readLine())
            {
                StringTokenizer cageInfo = new StringTokenizer(line, " (,)");

                //Le prime 3 informazioni sono necessarie alla costruzione della gabbia
                int target = Integer.parseInt(cageInfo.nextToken());
                String operation = cageInfo.nextToken();
                int cageDim = Integer.parseInt(cageInfo.nextToken());


                Cage cage = new Cage(target,operation,cageDim);

                //Le successive informazioni servono a costruire la griglia posizionando le celle
                for (int i = 0; i < cageDim; i++)
                {   int row = Integer.parseInt(cageInfo.nextToken());
                    int col = Integer.parseInt(cageInfo.nextToken());

                    griglia[row][col] = new Cell(row, col, cage);
                    cage.addCell(griglia[row][col]);
                }
            }

            gioco = new Gioco(griglia);

            setChanged();
            notifyObservers(false);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void risolviGioco()
    {   if(gioco == null)
            throw new InvalidStateException("Non è presente un'istanza di gioco");

        gioco.risolvi();
    }

    public boolean impostaNumero(int row, int col, int num)
    {   boolean valido = false;
        if(num == 0) {
            valido = true;
            gioco.assegna(num, gioco.getGriglia()[row][col]);
        }
        else if(gioco.validNumber(num, gioco.getGriglia()[row][col]))
        {   gioco.assegna(num, gioco.getGriglia()[row][col]);
            valido = true;
        }


        setChanged();
        notifyObservers(valido);

        return valido;
    }

    public Cell[][] getGriglia(){ return gioco.getGriglia();}

    public int getNumSoluzioni()
    {   return gioco.getSoluzioni().size();
    }

    public void impostaSoluzione(int num)
    {   if(num < 0 || num > getNumSoluzioni())
            throw new IndexOutOfBoundsException();

        gioco.setGriglia(gioco.getSoluzioni().get(num));

        setChanged();
        notifyObservers(false);
    }



    public Gioco getGioco(){ return gioco;}

}
