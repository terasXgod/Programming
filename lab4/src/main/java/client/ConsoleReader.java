package client;

import client.checker.CommandChecker;
import client.parsing.CommandFactory;
import client.parsing.VehicleReader;
import common.Request;
import common.commands.Command;
import common.dto.command.CommandPayload;
import common.dto.command.FuelPayload;
import common.dto.command.IdPayload;
import common.dto.command.UpdatePayload;
import common.dto.command.VehiclePayload;
import common.dto.model.VehicleDto;
import exceptions.InvalidCommandException;

import java.util.Scanner;

/**
 * Reads user input (or script input) and builds Requests for the server.
 */
public class ConsoleReader {

    private final Scanner scanner;
    private final CommandChecker commandChecker = new CommandChecker();
    private final VehicleReader vehicleMaker = new VehicleReader();
    private final CommandFactory commandFactory = new CommandFactory();

    /**
     * Creates a reader bound to the provided scanner (console or script).
     *
     * @param scanner input source
     */
    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a line from the scanner and parses it into a Request.
     *
     * @return prepared request ready for sending
     */
    public Request read() {
        System.out.print("> ");
        String input = scanner.nextLine().trim();
        return parseInput(input, scanner);
    }

    /**
     * Parse a command line and build a Request.
     * Uses the provided scanner to read vehicle fields if needed (e.g. from a script file).
     *
     * @param input raw line with command and args
     * @param inputScanner scanner to read additional fields
     * @return constructed Request instance
     */
    public Request parseInput(String input, Scanner inputScanner) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Empty input. Please enter a command.");
        }

        String[] parts = input.split("\\s+");
        String commandName = parts[0];
        if (!commandChecker.isValid(commandName)) {
            throw new IllegalArgumentException("Unknown command: " + commandName + ". Type 'help' for a list of commands.");
        }

        // execute_script is handled entirely on the client side — return a special Request with just the fileName
        if ("execute_script".equals(commandName)) {
            if (parts.length < 2) {
                throw new IllegalArgumentException("Argument expected for command: execute_script");
            }
            return Request.scriptRequest(parts[1]);
        }

        CommandPayload payload = null;
        Integer idArg = null;
        if (commandChecker.needArg(commandName)) {
            if (parts.length < 2) {
                throw new IllegalArgumentException("Argument expected for command: " + commandName);
            }
            try {
                switch (commandName) {
                    case "remove_by_id" -> idArg = Integer.parseInt(parts[1]);
                    case "update"       -> idArg = Integer.parseInt(parts[1]);
                    case "filter_less_than_fuel_consumption" -> payload = new FuelPayload(Double.parseDouble(parts[1]));
                    default             -> payload = null;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid argument for command '" + commandName + "': " + parts[1]);
            }
        }

        VehicleDto dto = null;
        if (commandChecker.needObject(commandName)) {
            dto = vehicleMaker.readVehicle(inputScanner);
        }

        // Compose payloads per command
        switch (commandName) {
            case "add" -> {
                if (dto == null) {
                    throw new IllegalArgumentException("Vehicle data is required for add.");
                }
                payload = new VehiclePayload(dto);
            }
            case "update" -> {
                if (idArg == null || dto == null) {
                    throw new IllegalArgumentException("Both id and vehicle are required for update.");
                }
                payload = new UpdatePayload(idArg, dto);
            }
            case "remove_greater", "remove_lower" -> {
                if (dto == null) {
                    throw new IllegalArgumentException("Vehicle data is required for this command.");
                }
                payload = new VehiclePayload(dto);
            }
            case "remove_by_id" -> {
                if (idArg == null) {
                    throw new IllegalArgumentException("ID is required for remove_by_id.");
                }
                payload = new IdPayload(idArg);
            }
            default -> {
                // payload already set above for commands like filter_less_than_fuel_consumption
            }
        }

        Command command;
        try {
            command = commandFactory.getCommand(commandName);
        } catch (InvalidCommandException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        if (payload != null) {
            command.setPayload(payload);
        }

        return new Request(command, payload);
    }
}
