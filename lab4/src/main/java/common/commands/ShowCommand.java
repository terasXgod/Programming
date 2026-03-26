package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;
import common.entity.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Command to print all vehicles currently stored in the collection.
 */
public class ShowCommand implements Command {


    /**
     * Prints all vehicles sorted by id.
     *
     * @param collectionManager source collection manager
     * @return formatted list or empty notice
     */
    @Override
    public String execute(CollectionManager collectionManager) {
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

    /**
     * Returns the command keyword.
     *
     * @return {@code "show"}
     */
    @Override
    public String getName() {
        return "show";
    }

    /**
     * Show accepts no arguments.
     *
     * @param arg unused argument container
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // no payload required; ignore any provided data
    }
}
