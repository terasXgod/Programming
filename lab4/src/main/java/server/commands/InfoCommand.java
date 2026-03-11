package server.commands;

import server.collection.CollectionManager;

public class InfoCommand implements Command {

    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        return "Collection type: " + collectionManager.getCollectionType().getSimpleName() +
                "\nInitialization date: " + collectionManager.getInitDate() +
                "\nCollection size: " + collectionManager.getLength();
    }

    @Override
    public String getName() {
        return "info";
    }

}
