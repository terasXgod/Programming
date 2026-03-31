package client.parsing;

import common.commands.*;
import exceptions.InvalidCommandException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Provides command instances by name and injects arguments when needed.
 */
public class CommandFactory {

    private final Map<String, Supplier<Command>> commands = new LinkedHashMap<>();

    /**
     * Registers all supported commands for later lookup.
     */
    public CommandFactory() {
        register("add",                                 AddCommand::new);
        register("clear",                               ClearCommand::new);
        register("print_field_descending_engine_power", EnginePowerCommand::new);
        register("exit",                                ExitCommand::new);
        register("filter_less_than_fuel_consumption",   FilterLessThanFuelConsumptionCommand::new);
        register("group_counting_by_creation_date",     GroupByDateCommand::new);
        register("help",                                HelpCommand::new);
        register("history",                             HistoryCommand::new);
        register("info",                                InfoCommand::new);
        register("remove_by_id",                        RemoveByIdCommand::new);
        register("remove_greater",                      RemoveGreaterCommand::new);
        register("remove_lower",                        RemoveLowerCommand::new);
        register("show",                                ShowCommand::new);
        register("update",                              UpdateCommand::new);
    }

    /**
     * Returns a command instance for the given name and applies its argument.
     *
     * @param commandName command keyword
     * @return initialized Command instance
     * @throws InvalidCommandException when the command is unknown
     */
    public Command getCommand(String commandName) throws InvalidCommandException {
        Supplier<Command> supplier = commands.get(commandName);
        if (supplier == null) {
            throw new InvalidCommandException("Unknown command: " + commandName);
        }
        Command command = supplier.get();
        return command;
    }

    /**
     * Internal helper to register a command supplier under its keyword.
     *
     * @param name command keyword
     * @param supplier constructor or supplier for the command
     */
    private void register(String name, Supplier<Command> supplier) {
        commands.put(name, supplier);
    }
}
