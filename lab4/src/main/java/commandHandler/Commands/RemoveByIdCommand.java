package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to remove a vehicle by its unique identifier.
 */
public class RemoveByIdCommand implements Command{

    private final Manager manager;
    private final int id;

    public RemoveByIdCommand(Manager editor, int id) {
        this.manager = editor;
        this.id = id;
    }
    @Override
    public void execute() {
        System.out.println(manager.removeById(id));
    }

    @Override
    public String toString() {
        return "remove_by_id";
    }
}
