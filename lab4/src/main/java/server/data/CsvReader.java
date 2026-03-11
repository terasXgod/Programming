package server.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader<T> {

    private final Class<T> type;
    Scanner scanner;

    public CsvReader(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public List<T> read(String filePath){
        List<T> list = new ArrayList<>();

        try {
            scanner = new Scanner(new File(filePath));
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            Integer idx = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    list.add((T) LineParser.parse(line, idx));
                } catch (RuntimeException e) {
                    System.err.println("Error reading line " + idx + ": " + e.getMessage());
                    System.err.println("Skipped line: " + line);
                }
                idx++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return list;
    }
}
