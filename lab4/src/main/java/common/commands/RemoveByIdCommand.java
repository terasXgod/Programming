package common.commands;

import common.dto.command.CommandPayload;
import common.dto.command.IdPayload;
import server.collection.CollectionManager;
import common.entity.Vehicle;

/**
 * Command to remove a vehicle by its unique identifier.
 */
public class RemoveByIdCommand implements Command {

    private Integer id;

    /**
     * Captures the identifier of the vehicle to remove.
     *
     * @param arg argument container with an id
     * @throws IllegalArgumentException when id is missing
     */
    @Override
    public void setPayload(CommandPayload payload) {
        if (!(payload instanceof IdPayload ip)) {
            throw new IllegalArgumentException("Error: no ID provided.");
        }
        this.id = ip.id();
    }

    /**
     * Removes a vehicle by id if it exists.
     *
     * @param collectionManager collection manager used for lookup and removal
     * @return result message describing the outcome
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        Vehicle vehicle = collectionManager.getElementById(id);
        if (vehicle == null) {
            return "Error: no vehicle found with ID " + id + ".";
        }
        collectionManager.removeById(id);
        return "Vehicle with ID " + id + " has been removed.";
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "remove_by_id"}
     */
    @Override
    public String getName() {
        return "remove_by_id";
    }
}
