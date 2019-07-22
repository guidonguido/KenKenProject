package command;


import java.util.LinkedList;

public class HistoryCommandHaldler implements CommandHandler {

    public static final Command UNDO = new NonExecutableCommand();
    public static final Command REDO = new NonExecutableCommand();



    private static class NonExecutableCommand implements Command{

        @Override
        public boolean doIt() {
            throw new NoSuchMethodError();
        }

        @Override
        public boolean undoIt() {
            throw new NoSuchMethodError();
        }
    }

    private int maxHistoryLength = 100;

    private LinkedList<Command> history = new LinkedList<>();

    private LinkedList<Command> redoList = new LinkedList<>();


    public boolean handle(Command cmd) {
        if (cmd == UNDO) {
            return undo();

        }
        if (cmd == REDO) {
            return redo();

        }

        if(cmd instanceof RisolviCommand || cmd instanceof ScegliFileCommand)
        {   history.clear();
            redoList.clear();
            return cmd.doIt();
        }

        if(cmd.doIt()) {
            addToHistory(cmd);
            return  true;
        }

        return false; //Comando non gestito perchè non eseguito

    }

    private boolean redo() {
        if (redoList.size() > 0) {
            Command redoCmd = redoList.removeLast();
            redoCmd.doIt();
            history.addLast(redoCmd);

            return true;
        }

        return false;
    }

    private boolean undo() {
        if (history.size() > 0) {
            Command undoCmd = history.removeLast();
            undoCmd.undoIt();
            redoList.addLast(undoCmd);

            return true;
        }

        return false;
    }

    private void addToHistory(Command cmd) {
        history.addLast(cmd);
        if (history.size() > maxHistoryLength) {
            history.removeFirst();
        }

    }
}
