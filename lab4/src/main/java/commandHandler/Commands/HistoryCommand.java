package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to print the history of the last executed commands.
 */
public class HistoryCommand implements Command{
    private final Manager manager;

    public HistoryCommand(Manager editor) {
        this.manager = editor;
    }

    public void execute() {
        System.out.println(manager.history());
    }

    @Override
    public String toString() {
        return "history";
    }
}
