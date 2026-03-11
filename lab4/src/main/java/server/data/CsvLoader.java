package server.data;

import common.model.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Utility for writing a set of {@link Vehicle} objects to a CSV
 * file.
 */
public class CsvLoader {

    public static void save(String fileName, Set<Vehicle> vehicles) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("id,name,x,y,creationDate,enginePower,numberOfWheels,fuelConsumption,type");
            bufferedWriter.newLine();
            for (Vehicle v : vehicles) {
                bufferedWriter.write(v.toSave());
                bufferedWriter.newLine();
            }
        }
    }
}
