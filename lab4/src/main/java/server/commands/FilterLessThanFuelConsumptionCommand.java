package server.commands;

import server.collection.CollectionManager;
import common.model.Vehicle;

/**
 * Command to display vehicles whose fuel consumption is less than a given threshold.
 */
public class FilterLessThanFuelConsumptionCommand implements Command {

    private final CollectionManager collectionManager;
    private Double fuelConsumption;

    public FilterLessThanFuelConsumptionCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void setArg(String arg) {
        this.fuelConsumption = Double.parseDouble(arg);
    }

    @Override
    public String execute() {
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

    @Override
    public String getName() {
        return "filter_less_than_fuel_consumption";
    }
}
