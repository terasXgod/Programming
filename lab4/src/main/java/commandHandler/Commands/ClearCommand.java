package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to clear the entire vehicle collection.
 */
public class ClearCommand implements Command{

    private final Manager manager;

    public ClearCommand(Manager editor) {
        this.manager = editor;
    }

    public void execute() {
        System.out.println(manager.clear());
    }

    @Override
    public String toString() {
        return "clear";
    }
}
