package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;
import common.entity.Vehicle;

import java.util.stream.Collectors;

/**
 * Command to group vehicles by creation date and display counts.
 */
public class GroupByDateCommand implements Command {

    private CollectionManager collectionManager;


    /**
     * Groups vehicles by creation date and prints the counts per date.
     *
     * @param collectionManager source of vehicles
     * @return formatted grouping summary or empty notice
     */
    @Override
    public String execute(CollectionManager collectionManager) {
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

    /**
     * Returns the command keyword.
     *
     * @return {@code "group_counting_by_creation_date"}
     */
    @Override
    public String getName() {
        return "group_counting_by_creation_date";
    }

    /**
     * No arguments are needed for grouping.
     *
     * @param arg unused argument container
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // no payload required; ignore any provided data
    }
}
