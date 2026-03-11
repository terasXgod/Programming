package server;

import common.Request;
import common.Response;
import server.collection.CollectionManager;
import server.data.DataLoader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    private final int port;
    private final CollectionManager collectionManager;
    private final RequestHandler requestHandler;

    public ServerApp(int port, String filePath) {
        this.port = port;
        this.collectionManager = new CollectionManager(DataLoader.loadVehicles(filePath));
        this.requestHandler = new RequestHandler(collectionManager, filePath);
    }

    public void run() {
        System.out.println("Server is running on port " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Waiting for a client to connect...");

                try (Socket client = serverSocket.accept();
                     ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

                    System.out.println("Client connected: " + client.getInetAddress());
                    out.flush();

                    while (true) {
                        try {
                            Request request = (Request) in.readObject();
                            System.out.println("Received request: " + request.getCommand());

                            Response response = requestHandler.handle(request);
                            out.writeObject(response);
                            out.flush();

                            if ("exit".equals(request.getCommand())) {
                                System.out.println("Client requested exit.");
                                break;
                            }

                        } catch (EOFException e) {
                            System.out.println("Client disconnected.");
                            break;
                        } catch (ClassNotFoundException e) {
                            System.err.println("[ERROR] Invalid object received: " + e.getMessage());
                            out.writeObject(new Response("Invalid request format.", false));
                            out.flush();
                        }
                    }
                } catch (IOException e) {
                    System.err.println("[ERROR] Client connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("[FATAL] Server failed to start: " + e.getMessage());
        }
    }

}
