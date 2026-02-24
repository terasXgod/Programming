package commandHandler.Commands;

import commandHandler.Manager;
import collections.Vehicle;

public class AddCommand implements Command{

    private final Manager manager;
    private final Vehicle vehicle;

    public AddCommand(Manager editor, Vehicle vehicle) {
        this.manager = editor;
        this.vehicle = vehicle;
    }


    public void execute() {
        System.out.println(manager.add(vehicle));
    }

    @Override
    public String toString() {
        return "add";
    }
}
