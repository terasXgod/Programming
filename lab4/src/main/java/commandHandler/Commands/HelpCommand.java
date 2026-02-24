package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to display help text listing available commands.
 */
public class HelpCommand implements Command{

    private final Manager manager;

    public HelpCommand(Manager editor) {
        this.manager = editor;
    }

    public void execute() {
        System.out.println(manager.help());
    }

    @Override
    public String toString() {
        return "help";
    }
}
