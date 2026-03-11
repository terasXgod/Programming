package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

/**
 * Command to update an existing vehicle identified by id.
 */
public class UpdateCommand implements Command {

    private final CollectionManager collectionManager;
    private Integer id;
    private Vehicle vehicle;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setArg(String arg) {
        this.id = Integer.parseInt(arg);
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String execute() {
        if (id == null) {
            return "Error: no ID provided.";
        }
        if (vehicle == null) {
            return "Error: no vehicle provided.";
        }
        Vehicle existing = collectionManager.getElementById(id);
        if (existing == null) {
            return "Error: no vehicle found with ID " + id + ".";
        }
        collectionManager.removeById(id);
        vehicle.setId(id);
        collectionManager.addVehicle(vehicle);
        return "Vehicle with ID " + id + " has been updated.";
    }

    @Override
    public String getName() {
        return "update";
    }
}
