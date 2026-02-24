package data;

import collections.Vehicle;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * High-level entry point for loading vehicle data from the configured CSV
 * file (using {@link CsvReader}).
 */
public class DataLoader {
    private static final String filePath = DotEnvReader.getFileName();
    static CsvReader<Vehicle> reader = new CsvReader<>(Vehicle.class);

    public static Set<Vehicle> loadVehicles() {
        Set<Vehicle> vehicles = new LinkedHashSet<>();
        vehicles.addAll(reader.read(filePath));
        return vehicles;
    }

    public static String getFilePath() {
        return filePath;
    }
}
