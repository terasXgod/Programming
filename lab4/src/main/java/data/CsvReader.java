package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;

public class CsvReader<T> {

    private final Class<T> type;
    Scanner scanner;

    public CsvReader(Class<T> type) {
        this.type = type;
    }

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
                list.add((T) LineParser.parse(line, idx));
                idx++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } finally {
            scanner.close();
        }
        return list;
    }
}
