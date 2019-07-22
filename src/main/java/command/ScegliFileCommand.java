package command;

import model.backtracking.GiocoObservable;

import java.io.File;

public class ScegliFileCommand implements Command {

    private File file;
    private GiocoObservable gioco = GiocoObservable.getInstance();

    public ScegliFileCommand(File file)
    {   this.file = file;
    }

    @Override
    public boolean doIt() {
        gioco.newGame(file);
        return true;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
