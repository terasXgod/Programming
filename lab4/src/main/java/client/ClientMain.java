package client;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Client entry point that loads configuration and starts the interactive client.
 */
public class ClientMain{
    private static final Dotenv dotenv = Dotenv.load();
    public static final String HOST = dotenv.get("SERVER_HOST");
    public static final int PORT = Integer.parseInt(dotenv.get("SERVER_PORT"));

    /**
     * Launches the client using host/port from environment variables.
     *
     * @param args ignored CLI arguments
     */
    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp(HOST, PORT);
        clientApp.run();
    }
}
