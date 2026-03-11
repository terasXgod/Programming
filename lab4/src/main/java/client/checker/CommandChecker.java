package client.checker;

public class CommandChecker {

    public boolean needObject(String input) {
        String[] parts = input.split(" ");
        return switch (parts[0]) {
            case "add", "update", "remove_greater", "remove_lower" -> true;
            default -> false;
        };
    }

    public boolean needArg(String input) {
        String[] parts = input.split(" ");
        return switch (parts[0]) {
            case "update", "remove_by_id", "execute_script", "filter_less_than_fuel_consumption" -> true;
            default -> false;
        };
    }

    public boolean isValid(String command) {
        return switch (command) {
            case "help", "info", "show", "clear", "save", "exit", "history", "group_counting_by_creation_date",
                 "print_field_descending_engine_power", "add", "remove_greater", "remove_lower","update",
                 "remove_by_id", "execute_script", "filter_less_than_fuel_consumption" -> true;
            default -> false;
        };
    }
}
