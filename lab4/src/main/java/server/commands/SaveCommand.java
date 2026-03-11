package server.commands;

import server.collection.CollectionManager;
import server.data.CsvLoader;

/**
 * Command to save the current state of the collection to a CSV file.
 */
public class SaveCommand implements Command {

    private final String fileName;
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager, String fileName) {
        this.collectionManager = collectionManager;
        this.fileName = fileName;
    }

    @Override
    public String execute() {
        try {
            CsvLoader.save(fileName, collectionManager.getCollection());
            return "Collection has been saved to " + fileName + ".";
        } catch (Exception e) {
            return "Error: failed to save data: " + e.getMessage();
        }
    }

    @Override
    public String getName() {
        return "save";
    }
}
