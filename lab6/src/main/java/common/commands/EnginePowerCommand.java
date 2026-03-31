package common.commands;

import common.dto.command.CommandPayload;
import server.collection.CollectionManager;
import common.entity.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Command to list engine powers of all vehicles in descending order.
 */
public class EnginePowerCommand implements Command {

    private CollectionManager collectionManager;

    /**
     * Lists engine powers for all vehicles in descending order.
     *
     * @param collectionManager source of vehicles
     * @return formatted list or an empty-collection notice
     */
    @Override
    public String execute(CollectionManager collectionManager) {
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

    /**
     * Returns the command keyword.
     *
     * @return {@code "print_field_descending_engine_power"}
     */
    @Override
    public String getName() {
        return "print_field_descending_engine_power";
    }

    /**
     * This command ignores arguments; none are required.
     *
     * @param arg unused argument container
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        // no arguments required; ignore any provided payload
    }

}
