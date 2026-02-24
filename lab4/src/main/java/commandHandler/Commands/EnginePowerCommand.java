package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to list engine powers of all vehicles in descending order.
 */
public class EnginePowerCommand implements Command{

    private final Manager manager;

    public EnginePowerCommand(Manager editor) {
        this.manager = editor;
    }

    public void execute() {
        System.out.println(manager.print_field_descending_engine_power());
    }

    @Override
    public String toString() {
        return "print_field_descending_engine_power";
    }

}
