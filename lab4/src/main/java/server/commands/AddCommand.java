package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

import java.util.Objects;
import java.util.Set;

public class AddCommand implements Command {

    private final CollectionManager collectionManager;
    private Vehicle vehicle;

    public AddCommand(CollectionManager collectionManager) {
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
        Set<Vehicle> vehicles = collectionManager.getCollection();
        for (Vehicle v : vehicles) {
            if (Objects.equals(v.getId(), vehicle.getId())) {
                return "Error: a vehicle with ID " + vehicle.getId() + " already exists.";
            }
        }
        collectionManager.addVehicle(vehicle);
        return "Vehicle with ID " + vehicle.getId() + " has been added.";
    }

    @Override
    public String getName() {
        return "add";
    }
}
