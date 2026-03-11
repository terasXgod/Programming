package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

/**
 * Command to remove all vehicles with id greater than the given vehicle's id.
 */
public class RemoveGreaterCommand implements Command {

    private final CollectionManager collectionManager;
    private Vehicle vehicle;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String execute() {
        if (vehicle == null) {
            return "Error: no vehicle provided.";
        }
        int id = vehicle.getId();
        int removed = 0;
        var iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            Vehicle v = iterator.next();
            if (v.getId() > id) {
                iterator.remove();
                removed++;
            }
        }
        return removed + " vehicle(s) greater than ID " + id + " have been removed.";
    }

    @Override
    public String getName() {
        return "remove_greater";
    }
}
