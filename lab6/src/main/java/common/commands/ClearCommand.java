package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;

/**
 * Command to clear the entire vehicle collection.
 */
public class ClearCommand implements Command {

    private CollectionManager collectionManager;

    /**
     * Removes all vehicles from the collection.
     *
     * @param collectionManager target collection manager
     * @return confirmation message
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        collectionManager.clear();
        return "The collection has been cleared.";
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "clear"}
     */
    @Override
    public String getName() {
        return "clear";
    }

    /**
     * Clear does not require arguments; any provided data is ignored.
     *
     * @param arg unused argument container
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // no arguments required; ignore any provided payload
    }
}
