package server;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Application entry point that bootstraps configuration and starts the server.
 */
public class ServerMain {
    private static final Dotenv dotenv = Dotenv.load();
    public static final int PORT = Integer.parseInt(dotenv.get("SERVER_PORT"));
    private static final String FILE_PATH = dotenv.get("DATABASE_PATH");

    /**
     * Launches the server using environment-provided port and data file path.
     *
     * @param args ignored CLI arguments
     */
    public static void main(String[] args) {
        LoggingConfig.init();
        ServerApp serverApp = new ServerApp(PORT, FILE_PATH);
        serverApp.run();
    }
}
