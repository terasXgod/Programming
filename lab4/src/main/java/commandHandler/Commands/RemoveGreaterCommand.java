package commandHandler.Commands;

import commandHandler.Manager;
import collections.Vehicle;

/**
 * Command to remove all vehicles with id greater than a given value.
 */
public class RemoveGreaterCommand implements Command{
    private final Manager manager;
    private Vehicle vehicle;

    public RemoveGreaterCommand(Manager manager, Vehicle vehicle) {
        this.manager = manager;
        this.vehicle = vehicle;
    }


    @Override
    public void execute() {
        System.out.println(manager.removeGreater(vehicle));
    }

    @Override
    public String toString() {
        return "remove_greater";
    }
}
