package command;

import model.backtracking.GiocoObservable;


public class RisolviCommand implements Command{

    private GiocoObservable gioco = GiocoObservable.getInstance();


    @Override
    public boolean doIt() {
        gioco.risolviGioco();
        gioco.impostaSoluzione(0);
        return true;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
