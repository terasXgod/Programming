package common;

import common.commands.Command;
import common.dto.command.CommandPayload;

import java.io.Serial;
import java.io.Serializable;

/**
 * Transport object that wraps a command instance with its optional payload
 * for transmission between client and server.
 */
public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Command command;
    private final CommandPayload payload;
    private final boolean scriptRequest;
    private final String scriptFileName;

    /**
     * Creates a request with the provided command and payload.
     *
     * @param command command to execute
     * @param payload payload accompanying the command (may be null)
     */
    public Request(Command command, CommandPayload payload) {
        this.command = command;
        this.payload = payload;
        this.scriptRequest = false;
        this.scriptFileName = null;
    }

    private Request(String scriptFileName) {
        this.command = null;
        this.payload = null;
        this.scriptRequest = true;
        this.scriptFileName = scriptFileName;
    }

    /**
     * Creates a client-only request for the execute_script command.
     * This request is never serialized/sent to the server.
     *
     * @param fileName script file to load locally
     * @return configured script request
     */
    public static Request scriptRequest(String fileName) {
        return new Request(fileName);
    }

    /**
     * Indicates whether the request is intended only for client-side script execution.
     *
     * @return true when the request should not be sent to the server
     */
    public boolean isScriptRequest() {
        return scriptRequest;
    }

    /**
     * Returns the encapsulated command.
     *
     * @return command instance or null for script-only requests
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Returns the command name if a command is present.
     *
     * @return command name or null
     */
    public String getCommandName() {
        return command != null ? command.getName() : null;
    }

    /**
     * Returns the payload accompanying the command.
     *
     * @return payload or null
     */
    public CommandPayload getPayload() {
        return payload;
    }

    /**
     * Returns the script file name for client-side script execution.
     *
     * @return script file name or null
     */
    public String getScriptFileName() {
        return scriptFileName;
    }

    @Override
    public String toString() {
        return "command: " + command.toString() + payload != null ?  "" : (" payload: " + payload.toString());
    }
}
