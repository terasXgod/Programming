package data;

import collections.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Utility for writing a set of {@link Vehicle} objects to a CSV
 * file.
 */
public class CsvLoader {

    public static void load(String fileName, Set<Vehicle> vehicles) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.append("id,name,x,y,creationDate,enginePower,numberOfWheels,fuelConsumption,type\n");
            for(Vehicle v: vehicles) {
                bufferedWriter.append(v.toSave() + "\n");
            }
            bufferedWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
