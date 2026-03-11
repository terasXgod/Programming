package client;

import client.checker.CommandChecker;
import common.Request;
import common.model.Vehicle;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner;
    private final CommandChecker commandChecker = new CommandChecker();
    private final VehicleMaker vehicleMaker = new VehicleMaker();

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Request read() {
        System.out.print("> ");
        String input = scanner.nextLine().trim();
        return parseInput(input, scanner);
    }

    /**
     * Parse a command line and build a Request.
     * Uses the provided scanner to read vehicle fields if needed (e.g. from a script file).
     */
    public Request parseInput(String input, Scanner inputScanner) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Empty input. Please enter a command.");
        }

        String[] parts = input.split("\\s+");
        String command = parts[0];
        if (!commandChecker.isValid(command)) {
            throw new IllegalArgumentException("Unknown command: " + command + ". Type 'help' for a list of commands.");
        }

        Request request = new Request();
        request.setCommand(command);

        if (commandChecker.needArg(command)) {
            if (parts.length < 2) {
                throw new IllegalArgumentException("Argument expected for command: " + command);
            }
            request.setArg(parts[1]);
        }

        if (commandChecker.needObject(command)) {
            Vehicle vehicle = vehicleMaker.readVehicle(inputScanner);
            request.setVehicle(vehicle);
        }

        return request;
    }
}
