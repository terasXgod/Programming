package client.checker;

/**
 * Utility class to check if a command requires an argument or an object,
 * and to validate command names.
 */
public class CommandChecker {

    /**
     * Determines whether the command requires a complex object argument.
     *
     * @param input raw command name
     * @return true if a vehicle object is needed
     */
    public boolean needObject(String input) {
        String[] parts = input.split(" ");
        return switch (parts[0]) {
            case "add", "update", "remove_greater", "remove_lower" -> true;
            default -> false;
        };
    }

    /**
     * Determines whether the command requires a simple argument (id, file, number).
     *
     * @param input raw command name
     * @return true if an argument is required
     */
    public boolean needArg(String input) {
        String[] parts = input.split(" ");
        return switch (parts[0]) {
            case "update", "remove_by_id", "execute_script", "filter_less_than_fuel_consumption" -> true;
            default -> false;
        };
    }

    /**
     * Checks if the provided command name is supported.
     *
     * @param command raw command name
     * @return true if valid
     */
    public boolean isValid(String command) {
        return switch (command) {
            case "help", "info", "show", "clear", "exit", "history", "group_counting_by_creation_date",
                 "print_field_descending_engine_power", "add", "remove_greater", "remove_lower", "update",
                 "remove_by_id", "execute_script", "filter_less_than_fuel_consumption" -> true;
            default -> false;
        };
    }
}
