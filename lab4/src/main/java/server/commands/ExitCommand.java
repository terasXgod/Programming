package server.commands;

/**
 * Command to signal that the client wants to disconnect.
 */
public class ExitCommand implements Command {

    @Override
    public String execute() {
        return "Goodbye!";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
