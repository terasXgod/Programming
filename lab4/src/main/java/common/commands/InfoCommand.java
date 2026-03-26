package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;

public class InfoCommand implements Command {

    public CollectionManager collectionManager;

    /**
     * Returns meta-information about the collection: type, init date, and size.
     *
     * @param collectionManager manager providing collection metadata
     * @return formatted info block
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        return "Collection type: " + collectionManager.getCollectionType().getSimpleName() +
                "\nInitialization date: " + collectionManager.getInitDate() +
                "\nCollection size: " + collectionManager.getLength();
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "info"}
     */
    @Override
    public String getName() {
        return "info";
    }

    /**
     * Info takes no arguments.
     *
     * @param arg unused argument container
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // no payload required; ignore any provided data
    }


}
