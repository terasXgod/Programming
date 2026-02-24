package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to save the current state of the collection to a CSV file.
 */
public class SaveCommand implements Command{

    private final Manager manager;
    private final String fileName;

    public SaveCommand(Manager editor, String fileName) {
        this.manager = editor;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        System.out.println(manager.save(fileName));
    }

    @Override
    public String toString() {
        return "save";
    }
}
