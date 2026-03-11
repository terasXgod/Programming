package server;

public class ServerMain {
    public static final int PORT = 5555;
    private static final String DEFAULT_FILE_PATH = "dataBase/data.csv";

    public static void main(String[] args) {
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = System.getenv("FILE_NAME");
            if (filePath == null || filePath.isBlank()) {
                filePath = DEFAULT_FILE_PATH;
                System.out.println("[INFO] No file path provided, using default: " + filePath);
            }
        }

        ServerApp serverApp = new ServerApp(PORT, filePath);
        serverApp.run();
    }
}
