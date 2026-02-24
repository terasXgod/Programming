package commandHandler.Commands;

import commandHandler.Manager;

public class InfoCommand implements Command{

    private final Manager manager;

    public InfoCommand(Manager editor) {
        this.manager = editor;
    }

    @Override
    public void execute() {
        System.out.println(manager.info());
    }

    @Override
    public String toString() {
        return "info";
    }

}
