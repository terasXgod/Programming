package common.commands;

import common.dto.command.CommandPayload;
import common.dto.command.FuelPayload;
import server.collection.CollectionManager;
import common.entity.Vehicle;

/**
 * Command to display vehicles whose fuel consumption is less than a given threshold.
 */
public class FilterLessThanFuelConsumptionCommand implements Command {

    private CollectionManager collectionManager;
    private Double fuelConsumption;

    /**
     * Lists vehicles with fuel consumption below the provided threshold.
     *
     * @param collectionManager source of vehicles
     * @return matching vehicles or an error/empty message
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        if (fuelConsumption == null) {
            return "Error: no fuel consumption value provided.";
        }
        StringBuilder sb = new StringBuilder();
        for (Vehicle v : collectionManager.getCollection()) {
            Double fuel = v.getFuelConsumption();
            if (fuel != null && fuel < fuelConsumption) {
                sb.append(v).append("\n");
            }
        }
        if (sb.isEmpty()) {
            return "No vehicles found with fuel consumption less than " + fuelConsumption + ".";
        }
        return sb.toString();
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "filter_less_than_fuel_consumption"}
     */
    @Override
    public String getName() {
        return "filter_less_than_fuel_consumption";
    }

    /**
     * Accepts the fuel consumption threshold as the argument.
     *
     * @param arg argument container with a numeric value
     * @throws IllegalArgumentException when the threshold is missing or non-positive
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        if (!(payload instanceof FuelPayload fp)) {
            throw new IllegalArgumentException("Error: fuel consumption must be a positive number.");
        }
        double fuel = fp.fuelConsumption();
        if (fuel <= 0) {
            throw new IllegalArgumentException("Error: fuel consumption must be a positive number.");
        }
        this.fuelConsumption = fuel;
    }
}
