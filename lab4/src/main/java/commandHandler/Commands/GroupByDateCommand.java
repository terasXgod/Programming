package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to group vehicles by creation date and display counts.
 */
public class GroupByDateCommand implements Command{

    Manager manager;

    public GroupByDateCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        System.out.println(manager.groupCountingByCreationDate());
    }

    @Override
    public String toString() {
        return "group_counting_by_creation_date";
    }
}
