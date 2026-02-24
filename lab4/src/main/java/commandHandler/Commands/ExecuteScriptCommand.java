/**
 * Command that reads subsequent commands from a file and executes them in
 * sequence.  The actual file handling is delegated to {@link commandHandler.Manager}.
 */
package commandHandler.Commands;

import commandHandler.Manager;

public class ExecuteScriptCommand implements Command{

    private final Manager manager;
    private final String fileName;

    public ExecuteScriptCommand(Manager manager, String fileName) {
        this.manager = manager;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        String result = manager.executeScript(fileName);
        System.out.println(result);
    }

    @Override
    public String toString() {
        return "execute_script";
    }
}
