package commandHandler.Commands;

import commandHandler.Manager;

/**
 * Command to display vehicles whose fuel consumption is less than a given
 * threshold.
 */
public class FilterLessThanFuelConsumptionCommand implements Command{

    Manager manager;
    Double fuelConsumption;

    public FilterLessThanFuelConsumptionCommand(Manager manager, Double fuelConsumption) {
        this.manager = manager;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public void execute() {
        System.out.println(manager.filterLessThanFuelConsumption(fuelConsumption));
    }

    @Override
    public String toString() {
        return "filter_less_than_fuel_consumption";
    }
}
