package commandHandler.Commands;

import commandHandler.Manager;
import collections.Vehicle;

/**
 * Command to update an existing vehicle identified by id.
 */
public class UpdateCommand implements Command{

    private final Manager manager;
    private final int id;
    private final Vehicle vehicle;


    public UpdateCommand(Manager editor, int id, Vehicle vehicle) {
        this.manager = editor;
        this.id = id;
        this.vehicle = vehicle;
    }

    @Override
    public void execute() {
        System.out.println(manager.update(id, vehicle));
    }

    @Override
    public String toString() {
        return "update";
    }
}
