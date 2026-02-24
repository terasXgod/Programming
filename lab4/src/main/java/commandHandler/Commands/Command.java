package commandHandler.Commands;

/**
 * Represents an executable operation in the application.  Commands are created
 * by {@link data.CommandParser} and executed by {@link commandHandler.Menu}.
 */
public interface Command {
    void execute();
}
