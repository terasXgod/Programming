package server;

import common.Request;
import common.Response;
import common.commands.Command;
import common.commands.HistoryCommand;
import common.dto.command.CommandPayload;
import common.dto.command.HistoryPayload;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.collection.CollectionManager;
import server.data.DataLoader;
import server.history.HistoryService;

/**
 * Dispatches incoming requests to their commands and manages command history population.
 */
public class RequestHandler {

    private final CollectionManager collectionManager;
    private final HistoryService historyService;
    private final Logger logger = Logger.getLogger(RequestHandler.class.getName());

    /**
     * Creates a handler bound to collection and history services.
     *
     * @param collectionManager manager used by commands
     * @param historyService history tracker for executed commands
     */
    public RequestHandler(CollectionManager collectionManager, HistoryService historyService) {
        this.collectionManager = collectionManager;
        this.historyService = historyService;
    }

    /**
     * Validates and executes the request command, appending to history and returning a Response.
     *
     * @param request incoming request
     * @return response containing result message and status
     */
    public Response handle(Request request) {
        if (request == null) {
            logger.log(Level.SEVERE, "Request is null!");
            return new Response("[ERROR] Received null request.", false);
        }

        if (request.isScriptRequest()) {
            return new Response("[ERROR] Script requests must be handled client-side.", false);
        }

        Command command = request.getCommand();
        if (command == null) {
            logger.log(Level.SEVERE, "Received null command.");
            return new Response("[ERROR] Request contains no command.", false);
        }
        logger.log(Level.INFO, String.format("Received command: %s", command.getName()));

        try {
            CommandPayload payload = request.getPayload();
            if (command instanceof HistoryCommand) {
                payload = new HistoryPayload(historyService.getHistory());
            }
            if (payload != null) {
                command.setPayload(payload);
            }
            String result = command.execute(collectionManager);
            if ("exit".equals(command.getName())) {
                DataLoader.saveVehicles(collectionManager.getCollection());
            }
            historyService.add(command.getName());
            logger.log(Level.INFO, String.format("Executed command: %s", command.getName()));
            return new Response(result, true);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return new Response("[ERROR] Command execution failed: " + e.getMessage(), false);
        }
    }

    /**
     * Exposes the collection manager used by commands.
     *
     * @return collection manager reference
     */
    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
