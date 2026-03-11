package client;

public class ClientMain{
    public static final String HOST = "localhost";
    public static final int PORT = 5555;

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp(HOST, PORT);
        clientApp.run();
    }
}

