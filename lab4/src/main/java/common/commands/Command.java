package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;

import java.io.Serializable;
import java.util.List;

/**
 * Base contract for all commands that can be executed on the server collection.
 */
public interface Command extends Serializable {

    /**
     * Executes command logic against the provided collection manager.
     *
     * @param collectionManager target collection coordinator
     * @return user-facing message describing the outcome
     */
    String execute(CollectionManager collectionManager);

    /**
     * Returns the unique command keyword used by the client.
     *
     * @return command name
     */
    String getName();

    /**
     * Supplies a typed payload when the command requires it.
     * Default implementation ignores payloads for commands without parameters.
     *
     * @param payload payload required by the command
     * @throws IllegalArgumentException when the payload is invalid for the command
     */
    default void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // default no-op
    }
}
