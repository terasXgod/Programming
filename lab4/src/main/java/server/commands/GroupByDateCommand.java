package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

import java.util.stream.Collectors;

/**
 * Command to group vehicles by creation date and display counts.
 */
public class GroupByDateCommand implements Command {

    private final CollectionManager collectionManager;

    public GroupByDateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        if (collectionManager.getCollection().isEmpty()) {
            return "Collection is empty.";
        }

        return collectionManager.getCollection().stream()
                .collect(Collectors.groupingBy(
                        Vehicle::getCreationDate,
                        Collectors.counting()))
                .entrySet().stream()
                .map(e -> e.getKey() + " : " + e.getValue())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getName() {
        return "group_counting_by_creation_date";
    }
}
