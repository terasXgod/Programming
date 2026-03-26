package client;

import client.parsing.CommandFactory;
import common.Request;
import common.commands.Command;
import common.dto.command.CommandPayload;
import exceptions.InvalidCommandException;

/**
 * Factory helper that builds {@link common.Request} objects from raw input.
 */
public class RequestMaker {

    private final CommandFactory commandFactory = new CommandFactory();

    /**
     * Builds a request for the given command name and argument.
     *
     * @param commandName user-entered command keyword
     * @param payload payload accompanying the command
     * @return constructed request ready for transport
     * @throws InvalidCommandException when the command name is unknown or invalid
     */
    public Request makeRequest(String commandName, CommandPayload payload) throws InvalidCommandException {
        Command cmd = commandFactory.getCommand(commandName);
        if (payload != null) {
            cmd.setPayload(payload);
        }
        return new Request(cmd, payload);
    }
}
