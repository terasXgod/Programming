package server;

import common.Request;
import common.Response;
import common.model.Vehicle;
import server.collection.CollectionManager;
import server.commands.*;

import java.util.*;

public class RequestHandler {

    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final List<String> history = new ArrayList<>();
    private static final int MAX_HISTORY = 13;

    public RequestHandler(CollectionManager collectionManager, String filePath) {
        register(new AddCommand(collectionManager));
        register(new ClearCommand(collectionManager));
        register(new EnginePowerCommand(collectionManager));
        register(new ExitCommand());
        register(new FilterLessThanFuelConsumptionCommand(collectionManager));
        register(new GroupByDateCommand(collectionManager));
        register(new HelpCommand());
        HistoryCommand historyCommand = new HistoryCommand(history);
        register(historyCommand);
        register(new InfoCommand(collectionManager));
        register(new RemoveByIdCommand(collectionManager));
        register(new RemoveGreaterCommand(collectionManager));
        register(new RemoveLowerCommand(collectionManager));
        register(new SaveCommand(collectionManager, filePath));
        register(new ShowCommand(collectionManager));
        register(new UpdateCommand(collectionManager));
    }

    private void register(Command command) {
        commands.put(command.getName(), command);
    }

    public Response handle(Request request) {
        String name = request.getCommand();

        Command command = commands.get(name);
        if (command == null) {
            return new Response("Unknown command: " + name, false);
        }

        try {
            String arg = request.getArg();
            Vehicle vehicle = request.getVehicle();

            command.setArg(arg);
            command.setVehicle(vehicle);

            String result = command.execute();

            history.add(name);
            if (history.size() > MAX_HISTORY) {
                history.remove(0);
            }

            return new Response(result, true);
        } catch (Exception e) {
            return new Response("[ERROR] " + e.getMessage(), false);
        }
    }
}
