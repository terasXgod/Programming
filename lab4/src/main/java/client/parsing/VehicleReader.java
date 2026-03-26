package client.parsing;

import common.entity.Coordinates;
import common.entity.VehicleType;
import common.dto.model.VehicleDto;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Collects and validates vehicle fields from user or script input, producing a VehicleDto.
 */
public class VehicleReader {

    /**
     * Interactively reads all vehicle fields from the scanner and builds a DTO.
     *
     * @param scanner source of user/script input
     * @return populated VehicleDto instance
     */
    public VehicleDto readVehicle(Scanner scanner) {

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

        /*System.out.print("Enter creation date (yyyy-MM-dd) or press Enter to use current date: ");
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
        }*/

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

        return new VehicleDto(name, coordinates, enginePower, numberOfWheels, fuelConsumption, type);
    }

    /**
     * Checks if the provided string matches a known VehicleType.
     *
     * @param input raw user input
     * @return true if input is a valid enum constant
     */
    private boolean isValidVehicleType(String input) {
        for (VehicleType t : VehicleType.values()) {
            if (t.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
