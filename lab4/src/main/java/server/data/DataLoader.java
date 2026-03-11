package server.data;

import common.model.Vehicle;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * High-level entry point for loading vehicle data from the configured CSV
 * file (using {@link CsvReader}).
 */
public class DataLoader {
    static CsvReader<Vehicle> reader = new CsvReader<>(Vehicle.class);

    public static LinkedHashSet<Vehicle> loadVehicles(String filePath) {
        LinkedHashSet<Vehicle> vehicles = new LinkedHashSet<>();
        vehicles.addAll(reader.read(filePath));
        return vehicles;
    }
}
