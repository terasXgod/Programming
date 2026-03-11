package server.commands;

import server.collection.CollectionManager;

/**
 * Command to clear the entire vehicle collection.
 */
public class ClearCommand implements Command {

    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        collectionManager.clear();
        return "The collection has been cleared.";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
