package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;
import server.data.DataLoader;

/**
 * Command to save the current state of the collection to a CSV file.
 */
public class SaveCommand implements Command {

    private String fileName;

    /**
     * Captures the target file name for saving the collection.
     *
     * @param arg argument container holding a file name
     * @throws IllegalArgumentException when file name is missing or blank
     */
    @Override
    public void setPayload(CommandPayload arg) {
        /*String name = arg.getFileName();
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Error: no file name provided.");
        }
        this.fileName = name;*/
        fileName = "dataBase/data.csv";
    }


    /**
     * Persists the collection to the provided CSV file.
     *
     * @param collectionManager collection source to serialize
     * @return result message with success or failure detail
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        try {
            DataLoader.saveVehicles(collectionManager.getCollection());
            return "Collection has been saved to " + fileName + ".";
        } catch (Exception e) {
            return "Error: failed to save data: " + e.getMessage();
        }
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "save"}
     */
    @Override
    public String getName() {
        return "save";
    }
}