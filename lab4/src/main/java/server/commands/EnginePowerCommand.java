package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Command to list engine powers of all vehicles in descending order.
 */
public class EnginePowerCommand implements Command {

    private final CollectionManager collectionManager;

    public EnginePowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        if (collectionManager.getCollection().isEmpty()) {
            return "Collection is empty.";
        }
        ArrayList<Float> powers = new ArrayList<>();
        for (Vehicle v : collectionManager.getCollection()) {
            powers.add(v.getEnginePower());
        }
        powers.sort(Comparator.reverseOrder());

        StringBuilder sb = new StringBuilder("Engine powers in descending order:\n");
        for (Float pow : powers) {
            sb.append(pow).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return "print_field_descending_engine_power";
    }

}
