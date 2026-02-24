package data;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Wrapper around OpenCSV bean parsing to read typed records from a file.
 */
public class OpenCsvReader<T> {

    private final Class<T> type;

    public OpenCsvReader(Class<T> type) {
        this.type = type;
    }

    public List<T> read(String filePath) throws IOException {
        Reader reader = new FileReader(filePath, StandardCharsets.UTF_8);
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                .withType(type)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();

    }
}