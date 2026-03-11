package server.data;

import common.model.Coordinates;
import common.model.Vehicle;
import common.model.VehicleType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses a CSV-formatted line into a {@link Vehicle} instance.
 */
public class LineParser {
    public static Vehicle parse(String line, Integer idx) throws RuntimeException {
        try {
            String[] parts = line.split(",", -1);

            if (parts.length < 9) {
                throw new IllegalArgumentException("Not enough fields in line. Expected 9, got: " + parts.length);
            }

            int i = 0;
            Integer id = parts[i++].isEmpty() ? idx : Integer.parseInt(parts[i-1]);

            String name = parts[i++];
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle name cannot be empty");
            }

            Integer x = parts[i++].isEmpty() ? 0 : Integer.parseInt(parts[i-1]);
            Integer y = parts[i++].isEmpty() ? 0 : Integer.parseInt(parts[i-1]);

            LocalDateTime creationDate = parts[i++].isEmpty() ? LocalDateTime.now() :
                    LocalDateTime.parse(parts[i-1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            float enginePower = parts[i++].isEmpty() ? 0.0f : Float.parseFloat(parts[i-1]);

            Long numberOfWheels = parts[i++].isEmpty() ? 0L : Long.parseLong(parts[i-1]);

            double fuelConsumption = parts[i++].isEmpty() ? 0.0d : Double.parseDouble(parts[i-1]);

            if (parts[i++].isEmpty()) {
                throw new IllegalArgumentException("Vehicle type cannot be empty");
            }
            VehicleType type = VehicleType.valueOf(parts[i-1]);

            return new Vehicle(id, name, new Coordinates(x, y), creationDate, enginePower, numberOfWheels,
                    fuelConsumption, type);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric value in line: " + line + ". " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error parsing line: " + line + ". " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error parsing line: " + line + ". " + e.getMessage(), e);
        }
    }

    public static Vehicle parse(String line) {
        try {
            String[] parts = line.split(",", -1);

            if (parts.length < 9) {
                throw new IllegalArgumentException("Not enough fields in line. Expected 9, got: " + parts.length);
            }

            int i = 0;
            Integer id = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);

            String name = parts[i++];
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle name cannot be empty");
            }

            Integer x = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);
            Integer y = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);

            LocalDateTime creationDate = parts[i++].isEmpty() ? LocalDateTime.now() :
                    LocalDateTime.parse(parts[i-1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            float enginePower = parts[i++].isEmpty() ? 0.0f : Float.parseFloat(parts[i-1]);

            Long numberOfWheels = parts[i++].isEmpty() ? null : Long.parseLong(parts[i-1]);

            double fuelConsumption = parts[i++].isEmpty() ? 0.0d : Double.parseDouble(parts[i-1]);

            VehicleType type = parts[i++].isEmpty() ? null : VehicleType.valueOf(parts[i-1]);

            return new Vehicle(id, name, new Coordinates(x, y), creationDate, enginePower, numberOfWheels,
                    fuelConsumption, type);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric value in line: " + line + ". " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error parsing line: " + line + ". " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error parsing line: " + line + ". " + e.getMessage(), e);
        }
    }

}
