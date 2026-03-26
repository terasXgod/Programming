package server.data;

import common.entity.Vehicle;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * High-level entry point for loading vehicle data from the configured CSV
 * file (using {@link CsvReader}).
 */
public class DataLoader {
    static CsvReader<Vehicle> reader = new CsvReader<>(Vehicle.class);
    private final static String filePath = Dotenv.load().get("DATABASE_PATH");
    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());

    /**
     * Loads vehicles from the provided CSV file path into a LinkedHashSet.
     *
     * @return populated vehicle set
     */
    public static LinkedHashSet<Vehicle> loadVehicles() {
        LinkedHashSet<Vehicle> vehicles = new LinkedHashSet<>();
        vehicles.addAll(reader.read(filePath));
        return vehicles;
    }

    public static void saveVehicles(Set<Vehicle> vehicles) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write("id,name,x,y,creationDate,enginePower,numberOfWheels,fuelConsumption,type");
            bufferedWriter.newLine();
            for (Vehicle v : vehicles) {
                bufferedWriter.write(v.toSave());
                bufferedWriter.newLine();
            }
            logger.log(Level.INFO, "Vehicles saved");
        }
    }
}
