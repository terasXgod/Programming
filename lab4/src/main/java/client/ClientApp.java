package client;

import common.Request;
import common.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClientApp {
    private final String host;
    private final int port;
    private final Set<String> executingScripts = new HashSet<>();

    public ClientApp(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            ConsoleReader consoleReader = new ConsoleReader(scanner);
            out.flush();
            System.out.println("Connected to server.");
            System.out.println("Type 'help' for a list of commands.\n");

            while (true) {
                Request request;
                try {
                    request = consoleReader.read();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    continue;
                }

                if ("execute_script".equals(request.getCommand())) {
                    executeScript(request.getArg(), consoleReader, in, out);
                    continue;
                }

                try {
                    Response response = sendRequest(request, in, out);
                    System.out.println(response.getMessage());
                } catch (IOException e) {
                    System.err.println("[ERROR] Connection lost: " + e.getMessage());
                    break;
                }

                if ("exit".equals(request.getCommand())) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("[ERROR] Could not connect to server: " + e.getMessage());
        }
    }

    private void executeScript(String fileName, ConsoleReader consoleReader,
                               ObjectInputStream in, ObjectOutputStream out) {
        if (fileName == null || fileName.isBlank()) {
            System.err.println("[ERROR] No script file name provided.");
            return;
        }

        String absolutePath;
        try {
            absolutePath = new File(fileName).getCanonicalPath();
        } catch (IOException e) {
            System.err.println("[ERROR] Invalid file path: " + fileName);
            return;
        }

        if (executingScripts.contains(absolutePath)) {
            System.err.println("[ERROR] Recursion detected! Script '" + fileName + "' is already being executed.");
            return;
        }

        File scriptFile = new File(absolutePath);
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.err.println("[ERROR] Script file not found: " + fileName);
            return;
        }

        executingScripts.add(absolutePath);
        System.out.println("Executing script: " + fileName);

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

                if ("execute_script".equals(request.getCommand())) {
                    executeScript(request.getArg(), consoleReader, in, out);
                    continue;
                }

                try {
                    Response response = sendRequest(request, in, out);
                    System.out.println(response.getMessage());
                } catch (IOException e) {
                    System.err.println("[ERROR] Connection lost during script execution: " + e.getMessage());
                    break;
                }

                if ("exit".equals(request.getCommand())) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("[ERROR] Could not read script file: " + fileName);
        } finally {
            executingScripts.remove(absolutePath);
        }

        System.out.println("Script '" + fileName + "' execution finished.");
    }

    private Response sendRequest(Request request, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(request);
        out.flush();
        try {
            return (Response) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Invalid response from server.", e);
        }
    }

}
