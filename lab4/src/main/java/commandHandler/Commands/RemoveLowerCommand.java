package commandHandler.Commands;

import commandHandler.Manager;
import collections.Vehicle;

/**
 * Command to remove all vehicles with id lower than a given value.
 */
public class RemoveLowerCommand implements Command{

    private final Manager manager;
    private Vehicle vehicle;

    public RemoveLowerCommand(Manager manager, Vehicle vehicle) {
        this.manager = manager;
        this.vehicle = vehicle;
    }

    @Override
    public void execute() {
        System.out.println(manager.removeLower(vehicle));
    }

    @Override
    public String toString() {
        return "remove_lower";
    }
}
