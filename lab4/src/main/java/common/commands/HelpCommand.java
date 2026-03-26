package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;

/**
 * Command to display help text listing available commands.
 */
public class HelpCommand implements Command {

    /**
     * Returns a help message listing all available commands.
     *
     * @param collectionManager unused manager reference
     * @return formatted help text
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        return """
                Available commands:
                  help                                    - display this help message
                  info                                    - display collection info (type, init date, size)
                  show                                    - display all elements in the collection
                  add {element}                           - add a new element to the collection
                  update id {element}                     - update the element with the given id
                  remove_by_id id                         - remove an element by its id
                  clear                                   - clear the collection
                  execute_script file_name                - execute a script from a file
                  exit                                    - disconnect from the server
                  remove_greater {element}                - remove all elements greater than the given one
                  remove_lower {element}                  - remove all elements less than the given one
                  history                                 - show the last 13 commands
                  group_counting_by_creation_date         - group elements by creation date
                  filter_less_than_fuel_consumption value  - show elements with fuel consumption less than value
                  print_field_descending_engine_power     - show engine power values in descending order""";
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "help"}
     */
    @Override
    public String getName() {
        return "help";
    }

    /**
     * Help takes no arguments.
     *
     * @param arg unused argument container
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // help does not require payload
    }
}
