package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

/**
 * Command to remove a vehicle by its unique identifier.
 */
public class RemoveByIdCommand implements Command {

    private final CollectionManager collectionManager;
    private int id;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setArg(String arg) {
        this.id = Integer.parseInt(arg);
    }

    @Override
    public String execute() {
        Vehicle vehicle = collectionManager.getElementById(id);
        if (vehicle == null) {
            return "Error: no vehicle found with ID " + id + ".";
        }
        collectionManager.removeById(id);
        return "Vehicle with ID " + id + " has been removed.";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}
