package server.data;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Reads environment configuration (via dotenv) used by the application.
 */
public class DotEnvReader {
    private final static Dotenv dotenv = Dotenv.load();
    private final static String fileName = dotenv.get("FILE_NAME");

    public static  String getFileName() {
        return fileName;
    }
}
