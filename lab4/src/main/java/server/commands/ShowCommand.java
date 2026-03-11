package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Command to print all vehicles currently stored in the collection.
 */
public class ShowCommand implements Command {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        if (collectionManager.getCollection().isEmpty()) {
            return "Collection is empty.";
        }
        List<Vehicle> vehicleList = new ArrayList<>(collectionManager.getCollection());
        vehicleList.sort(Comparator.comparingInt(Vehicle::getId));

        StringBuilder result = new StringBuilder();
        for (Vehicle v : vehicleList) {
            result.append(v).append("\n");
        }
        return result.toString();
    }

    @Override
    public String getName() {
        return "show";
    }
}
