package data;

import collections.VehicleType;
import collections.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses a CSV-formatted line into a {@link Vehicle} instance.
 */
public class LineParser {
    public static Vehicle parse(String line, Integer idx) {
        String[] parts = line.split(",", -1);
        int i = 0;
        Integer id = parts[i++].isEmpty() ? idx : Integer.parseInt(parts[i-1]);

        String name = parts[i++];

        Integer x = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);
        Integer y = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);

        LocalDateTime creationDate = parts[i++].isEmpty() ? LocalDateTime.now() :
                LocalDateTime.parse(parts[i-1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        float enginePower = parts[i++].isEmpty() ? 0.0f : Float.parseFloat(parts[i-1]);

        Long numberOfWheels = parts[i++].isEmpty() ? null : Long.parseLong(parts[i-1]);

        double fuelConsumption = parts[i++].isEmpty() ? 0.0d : Double.parseDouble(parts[i-1]);

        VehicleType type = parts[i++].isEmpty() ? null : VehicleType.valueOf(parts[i-1]);

        return new Vehicle(id, name, x, y, creationDate, enginePower, numberOfWheels,
                fuelConsumption, type);
    }

    public static Vehicle parse(String line) {
        String[] parts = line.split(",", -1);
        int i = 0;
        Integer id = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);

        String name = parts[i++];

        Integer x = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);
        Integer y = parts[i++].isEmpty() ? null : Integer.parseInt(parts[i-1]);

        LocalDateTime creationDate = parts[i++].isEmpty() ? LocalDateTime.now() :
                LocalDateTime.parse(parts[i-1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        float enginePower = parts[i++].isEmpty() ? 0.0f : Float.parseFloat(parts[i-1]);

        Long numberOfWheels = parts[i++].isEmpty() ? null : Long.parseLong(parts[i-1]);

        double fuelConsumption = parts[i++].isEmpty() ? 0.0d : Double.parseDouble(parts[i-1]);

        VehicleType type = parts[i++].isEmpty() ? null : VehicleType.valueOf(parts[i-1]);

        return new Vehicle(id, name, x, y, creationDate, enginePower, numberOfWheels,
                fuelConsumption, type);
    }

}
