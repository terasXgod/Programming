package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;
import server.data.DataLoader;

import java.io.IOException;

/**
 * Command to signal that the client wants to disconnect.
 */
public class ExitCommand implements Command {

    /**
     * Returns a farewell message; server may close the connection afterwards.
     *
     * @param collectionManager unused manager reference
     * @return goodbye message
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        return "Goodbye!";

    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "exit"}
     */
    @Override
    public String getName() {
        return "exit";
    }

    /**
     * No arguments are required for exit.
     *
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // no arguments required; ignore any provided payload
    }
}
