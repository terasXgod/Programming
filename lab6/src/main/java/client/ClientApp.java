package client;

import common.Request;
import common.Response;
import exceptions.DeserializationException;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Client application that reads commands, sends requests to the server, and processes responses.
 * Supports interactive mode and execute_script handling with recursion prevention.
 */
public class ClientApp {
    private final String host;
    private final int port;
    private final Set<String> executingScripts = new HashSet<>();

    /**
     * Creates a client bound to a server host and port.
     *
     * @param host server hostname
     * @param port server port
     */
    public ClientApp(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Main client loop: connects to the server, reads commands, sends requests, and prints responses.
     */
    public void run() {
        boolean exit = false;
        while (!exit) {
            try (ClientSocket socket = new ClientSocket();
                 Scanner scanner = new Scanner(System.in)) {

                ConsoleReader consoleReader = new ConsoleReader(scanner);
                socket.connect(host, port);
                System.out.println("Connected to server.");

                while (!exit) {
                    Request request;
                    try {
                        request = consoleReader.read();
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        continue;
                    }

                    if (request.isScriptRequest()) {
                        try {
                            boolean scriptRequestedExit = executeScript(request.getScriptFileName(), consoleReader, socket);
                            if (scriptRequestedExit) {
                                exit = true;
                                break;
                            }
                        } catch (IOException e) {
                            System.err.println("[ERROR] Connection lost: " + e.getMessage());
                            break;
                        }
                        continue;
                    }

                    try {
                        Response response = sendRequest(request, socket);
                        System.out.println(response.getMessage());
                    } catch (IOException e) {
                        System.err.println("[ERROR] Connection lost: " + e.getMessage());
                        break;
                    }

                    if ("exit".equals(request.getCommandName())) {
                        exit = true;
                        break;
                    }
                }

            } catch (IOException e) {
                System.err.println("[ERROR] Could not connect to server: " + e.getMessage());
                if (exit) {
                    break;
                }
                System.out.println("Retrying in 5 seconds...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }

            } catch (DeserializationException e) {
                System.err.println("[ERROR] Failed to parse server response: " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Executes commands from a script file, handling nested execute_script calls safely.
     *
     * @param fileName script path provided by the user
     * @param consoleReader parser for commands within the script
     */
    private boolean executeScript(String fileName, ConsoleReader consoleReader, ClientSocket socket) throws IOException {
        if (fileName == null || fileName.isBlank()) {
            System.err.println("[ERROR] No script file name provided.");
            return false;
        }

        String absolutePath;
        try {
            absolutePath = new File(fileName).getCanonicalPath();
        } catch (IOException e) {
            System.err.println("[ERROR] Invalid file path: " + fileName);
            return false;
        }

        if (executingScripts.contains(absolutePath)) {
            System.err.println("[ERROR] Recursion detected! Script '" + fileName + "' is already being executed.");
            return false;
        }

        File scriptFile = new File(absolutePath);
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.err.println("[ERROR] Script file not found: " + fileName);
            return false;
        }

        executingScripts.add(absolutePath);
        System.out.println("Executing script: " + fileName);

        boolean exitRequested = false;

        try (Scanner fileScanner = new Scanner(scriptFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                System.out.println("> " + line);

                Request request;
                try {
                    request = consoleReader.parseInput(line, fileScanner);
                } catch (IllegalArgumentException e) {
                    System.err.println("[SCRIPT ERROR] " + e.getMessage());
                    continue;
                }

                if (request.isScriptRequest()) {
                    executeScript(request.getScriptFileName(), consoleReader, socket);
                    continue;
                }

                try {
                    Response response = sendRequest(request, socket);
                    System.out.println(response.getMessage());
                } catch (IOException e) {
                    System.err.println("[ERROR] Connection lost during script execution: " + e.getMessage());
                    throw e;
                }

                if ("exit".equals(request.getCommandName())) {
                    exitRequested = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("[ERROR] Could not read script file: " + fileName);
        } finally {
            executingScripts.remove(absolutePath);
        }

        System.out.println("Script '" + fileName + "' execution finished.");
        return exitRequested;
    }

    /**
     * Sends a single request to the server and waits for a response.
     *
     * @param request serialized request
     * @return response from server
     * @throws IOException when communication fails or response is invalid
     */
    private Response sendRequest(Request request, ClientSocket socket) throws IOException {
        socket.send(request);
        try {
            Response response = socket.receive();
            if (response == null) {
                throw new IOException("Server closed the connection");
            }
            return response;
        } catch (DeserializationException e) {
            throw new IOException("Failed to deserialize server response: " + e.getMessage(), e);
        }
    }

}
