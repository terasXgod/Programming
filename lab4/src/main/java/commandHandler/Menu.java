package commandHandler;

import commandHandler.Commands.Command;
import data.CommandParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Front‑end command dispatcher.  Converts text input into {@link commandHandler.Commands.Command}
 * instances and invokes them on the {@link Manager}.  Also maintains a history
 * of recently executed commands.
 */
public class Menu {

    private final Manager manager;
    private CommandParser commandParser;
    public static List<Command> logs = new ArrayList<>();

    public Menu(Manager manager) {
        this.manager = manager;
        this.commandParser = new CommandParser(manager);

    }

    public void doCommand(String s) {
        Command cmd = commandParser.parse(s);
        if (cmd == null) {
            System.out.println("[ERROR] unknown command: " + s);
            return;
        }
        
        cmd.execute();

        logs.add(cmd);
        if (logs.size() > 13) {
            logs.remove(0);
        }
    }
}
