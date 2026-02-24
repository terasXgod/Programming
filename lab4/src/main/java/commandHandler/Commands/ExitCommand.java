package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to terminate the application immediately.
 */
public class ExitCommand implements Command{

    private final Manager manager;

    public ExitCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.exit();
    }

    @Override
    public String toString() {
        return "exit";
    }
}
