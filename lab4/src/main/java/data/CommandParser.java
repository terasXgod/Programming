package data;

import commandHandler.Commands.*;
import commandHandler.Commands.ExecuteScriptCommand;
import commandHandler.Manager;

import java.util.HashMap;

/**
 * Parses individual command lines and produces the corresponding Command
 * implementation.  Responsible for argument splitting and validation.
 */
public class CommandParser {

    private Manager manager;
    HashMap<String, Command> commands;
    public CommandParser(Manager manager) {
        this.manager = manager;
        commands= new HashMap<>() {{
            put("info", new InfoCommand(manager));
            put("help", new HelpCommand(manager));
            put("clear", new ClearCommand(manager));
            put("show", new ShowCommand(manager));
            put("save", new SaveCommand(manager, DataLoader.getFilePath()));
            put("exit", new ExitCommand(manager));
            put("group_counting_by_creation_date", new GroupByDateCommand(manager));
            put("history", new HistoryCommand(manager));
            put("print_field_descending_engine_power", new EnginePowerCommand(manager));
        }};
    }

    public Command parse(String line) {
        String[] parts = line.split(" ", -1);
        String cmd = parts[0];

        if (parts.length == 1) {
            if (commands.containsKey(cmd)) {
                return commands.get(cmd);
            }
            if (requiresArgument(cmd)) {
                return errorArgExpected();
            }
            return unknownCommand();
        }

        if ((2 <= parts.length && parts[1].isEmpty()) || (3 == parts.length && parts[2].isEmpty())) {
            return errorArgExpected();
        }

        switch (cmd) {
            case "add":
                return new AddCommand(manager, LineParser.parse(parts[1].substring(1, parts[1].length()-1)));
            case "update":
                return new UpdateCommand(manager, Integer.parseInt(parts[1]), LineParser.parse(parts[2].substring(1, parts[2].length()-1)));
            case "remove_by_id":
                return new RemoveByIdCommand(manager, Integer.parseInt(parts[1]));
            case "execute_script":
                return new ExecuteScriptCommand(manager, parts[1]);
            case "remove_greater":
                return new RemoveGreaterCommand(manager, LineParser.parse(parts[1].substring(1, parts[1].length()-1)));
            case "remove_lower":
                return new RemoveLowerCommand(manager, LineParser.parse(parts[1].substring(1, parts[1].length()-1)));
            case "filter_less_than_fuel_consumption":
                return new FilterLessThanFuelConsumptionCommand(manager, Double.parseDouble(parts[1]));
            default:
                return unknownCommand();
        }
    }

    private Command errorArgExpected() {
        return new Command() {
            @Override
            public void execute() {
                System.out.println("[ERROR] argument expected");
            }
        };
    }

    private Command unknownCommand() {
        return new Command() {
            @Override
            public void execute() {
                System.out.println("[ERROR] Unknown command");
            }
        };
    }

    private boolean requiresArgument(String cmd) {
        switch (cmd) {
            case "add":
            case "update":
            case "remove_by_id":
            case "execute_script":
            case "remove_greater":
            case "remove_lower":
            case "filter_less_than_fuel_consumption":
                return true;
            default:
                return false;
        }
    }
}
