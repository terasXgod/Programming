package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to print all vehicles currently stored in the collection.
 */
public class ShowCommand implements Command{

    private final Manager manager;

    public ShowCommand(Manager editor) {
        this.manager = editor;
    }

    @Override
    public void execute() {
        System.out.println(manager.show());
    }

    @Override
    public String toString() {
        return "show";
    }
}
