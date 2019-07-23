package model.backtracking;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import sun.plugin.dom.exception.InvalidStateException;

import javax.xml.bind.annotation.W3CDomHandler;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GiocoObservableTest {

    public static Cell[][] inizializzaGriglia()
    {
        Cell[][] griglia = new Cell[4][4];
        Cage cage1 = new Cage(12,"multiplication",3);
        Cage cage2 = new Cage(2,"division",2);
        Cage cage3 = new Cage(11,"addition",4);
        Cage cage4 = new Cage(2,"subtraction",2);
        Cage cage5 = new Cage(2,"addition",1);
        Cage cage6 = new Cage(4,"addition",2);
        Cage cage7 = new Cage(3,"subtraction",2);

        griglia[0][0] = new Cell(0,0,cage1);
        griglia[0][1] = new Cell(0,1,cage1);
        griglia[0][2] = new Cell(0,2,cage2);
        griglia[0][3] = new Cell(0,3,cage2);
        griglia[1][0] = new Cell(1,0,cage3);
        griglia[1][1] = new Cell(1,1,cage1);
        griglia[1][2] = new Cell(1,2,cage4);
        griglia[1][3] = new Cell(1,3,cage5);
        griglia[2][0] = new Cell(2,0,cage3);
        griglia[2][1] = new Cell(2,1,cage3);
        griglia[2][2] = new Cell(2,2,cage4);
        griglia[2][3] = new Cell(2,3,cage6);
        griglia[3][0] = new Cell(3,0,cage3);
        griglia[3][1] = new Cell(3,1,cage7);
        griglia[3][2] = new Cell(3,2,cage7);
        griglia[3][3] = new Cell(3,3,cage6);

        cage1.addCell(griglia[0][0]);
        cage1.addCell(griglia[0][1]);
        cage1.addCell(griglia[1][1]);

        cage2.addCell(griglia[0][2]);
        cage2.addCell(griglia[0][3]);

        cage3.addCell(griglia[1][0]);
        cage3.addCell(griglia[2][0]);
        cage3.addCell(griglia[3][0]);
        cage3.addCell(griglia[2][1]);

        cage4.addCell(griglia[1][2]);
        cage4.addCell(griglia[2][2]);

        cage5.addCell(griglia[1][3]);

        cage6.addCell(griglia[2][3]);
        cage6.addCell(griglia[3][3]);

        cage7.addCell(griglia[3][1]);
        cage7.addCell(griglia[3][2]);

        return griglia;
    }

    @Test(expected = RuntimeException.class)
    public void ArisolviGiocoNonInizializzato() {
        GiocoObservable gioco = GiocoObservable.getInstance();
        gioco.risolviGioco();
    }

    @Test
    public void getInstance() {
        assertNotNull(GiocoObservable.getInstance());
    }

    @Test
    public void impostaNumero() {

        GiocoObservable gioco = GiocoObservable.getInstance();
        gioco.newGame(GiocoObservableTest.inizializzaGriglia());

        gioco.impostaNumero(1,1,3);

        assertEquals(new Integer(3),gioco.getGriglia()[1][1].getNumber());

    }

    @Test
    public void getGriglia() {
        GiocoObservable gioco = GiocoObservable.getInstance();
        Cell[][] griglia = GiocoObservableTest.inizializzaGriglia();
        gioco.newGame(griglia);

        assertSame(gioco.getGriglia(),griglia);
    }

    @Test
    public void getNumSoluzioni() {
        GiocoObservable gioco = GiocoObservable.getInstance();
        gioco.newGame(GiocoObservableTest.inizializzaGriglia());
        gioco.risolviGioco();
    }

    @Test(expected = Exception.class)
    public void impostaSoluzioneNonRisolto() {
        GiocoObservable gioco = GiocoObservable.getInstance();
        gioco.newGame(GiocoObservableTest.inizializzaGriglia());

        gioco.impostaSoluzione(gioco.getNumSoluzioni());
    }

}