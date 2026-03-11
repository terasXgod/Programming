package server.commands;

/**
 * Command to display help text listing available commands.
 */
public class HelpCommand implements Command {

    @Override
    public String execute() {
        return """
                Available commands:
                  help                                    - display this help message
                  info                                    - display collection info (type, init date, size)
                  show                                    - display all elements in the collection
                  add {element}                           - add a new element to the collection
                  update id {element}                     - update the element with the given id
                  remove_by_id id                         - remove an element by its id
                  clear                                   - clear the collection
                  save                                    - save the collection to a file
                  execute_script file_name                - execute a script from a file
                  exit                                    - disconnect from the server
                  remove_greater {element}                - remove all elements greater than the given one
                  remove_lower {element}                  - remove all elements less than the given one
                  history                                 - show the last 13 commands
                  group_counting_by_creation_date         - group elements by creation date
                  filter_less_than_fuel_consumption value  - show elements with fuel consumption less than value
                  print_field_descending_engine_power     - show engine power values in descending order""";
    }

    @Override
    public String getName() {
        return "help";
    }
}
