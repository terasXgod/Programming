package client;

import common.model.Coordinates;
import common.model.Vehicle;
import common.model.VehicleType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class VehicleMaker {

    public Vehicle readVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID: ");
        String idx = scanner.nextLine().trim();
        while (idx.isEmpty() || !idx.matches("-?\\d+")) {
            System.out.print("Invalid input. Please enter a valid integer for Vehicle ID: ");
            idx = scanner.nextLine().trim();
        }
        int id = Integer.parseInt(idx);

        System.out.print("Enter Vehicle name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Invalid input. Please enter a non-empty name: ");
            name = scanner.nextLine().trim();
        }

        System.out.print("Enter coordinate X: ");
        String xInput = scanner.nextLine().trim();
        while (!xInput.matches("-?\\d+")) {
            System.out.print("Invalid input. Please enter a valid integer for X: ");
            xInput = scanner.nextLine().trim();
        }

        System.out.print("Enter coordinate Y: ");
        String yInput = scanner.nextLine().trim();
        while (!yInput.matches("-?\\d+")) {
            System.out.print("Invalid input. Please enter a valid integer for Y: ");
            yInput = scanner.nextLine().trim();
        }
        Coordinates coordinates = new Coordinates(Integer.parseInt(xInput), Integer.parseInt(yInput));

        System.out.print("Enter creation date (yyyy-MM-dd) or press Enter to use current date: ");
        String dateInput = scanner.nextLine().trim();
        while (!dateInput.isEmpty() && !dateInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.print("Invalid format. Please enter a valid date (yyyy-MM-dd) or leave empty: ");
            dateInput = scanner.nextLine().trim();
        }
        LocalDateTime creationDate;
        if (dateInput.isEmpty()) {
            creationDate = LocalDateTime.now();
        } else {
            creationDate = LocalDateTime.parse(dateInput + "T00:00:00");
        }

        System.out.print("Enter engine power: ");
        String powerInput = scanner.nextLine().trim();
        while (!powerInput.matches("-?\\d+(\\.\\d+)?")) {
            System.out.print("Invalid input. Please enter a valid number for engine power: ");
            powerInput = scanner.nextLine().trim();
        }
        float enginePower = Float.parseFloat(powerInput);

        System.out.print("Enter number of wheels: ");
        String wheelsInput = scanner.nextLine().trim();
        while (!wheelsInput.matches("\\d+")) {
            System.out.print("Invalid input. Please enter a valid positive integer for number of wheels: ");
            wheelsInput = scanner.nextLine().trim();
        }
        Long numberOfWheels = Long.parseLong(wheelsInput);

        System.out.print("Enter fuel consumption: ");
        String fuelInput = scanner.nextLine().trim();
        while (!fuelInput.matches("-?\\d+(\\.\\d+)?")) {
            System.out.print("Invalid input. Please enter a valid number for fuel consumption: ");
            fuelInput = scanner.nextLine().trim();
        }
        double fuelConsumption = Double.parseDouble(fuelInput);

        String vehicleTypes = String.join(", ",
                Arrays.stream(VehicleType.values()).map(Enum::name).toArray(String[]::new));
        System.out.print("Enter Vehicle type (" + vehicleTypes + "): ");
        String typeInput = scanner.nextLine().trim();
        while (!isValidVehicleType(typeInput)) {
            System.out.print("Invalid input. Please enter a valid Vehicle type (" + vehicleTypes + "): ");
            typeInput = scanner.nextLine().trim();
        }
        VehicleType type = VehicleType.valueOf(typeInput.toUpperCase());

        return new Vehicle(id, name, coordinates, creationDate, enginePower, numberOfWheels, fuelConsumption, type);
    }

    private boolean isValidVehicleType(String input) {
        for (VehicleType t : VehicleType.values()) {
            if (t.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
