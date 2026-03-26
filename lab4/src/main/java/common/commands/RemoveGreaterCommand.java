package common.commands;

import common.dto.command.CommandPayload;
import common.dto.command.VehiclePayload;
import common.dto.model.VehicleDto;
import common.mapper.VehicleMapper;
import server.collection.CollectionManager;
import common.entity.Vehicle;

/**
 * Command to remove all vehicles with id greater than the given vehicle's id.
 */
public class RemoveGreaterCommand implements Command {

    private VehicleDto vehicleDto;

    /**
     * Accepts the vehicle DTO used as a cutoff for id comparison.
     *
     * @param arg argument container with vehicle data
     * @throws IllegalArgumentException when vehicle payload is missing
     */
    @Override
    public void setPayload(CommandPayload payload) {
        if (!(payload instanceof VehiclePayload vp) || vp.vehicle() == null) {
            throw new IllegalArgumentException("Error: argument does not contain a valid vehicle.");
        }
        this.vehicleDto = vp.vehicle();
    }

    /**
     * Removes all vehicles whose ids exceed the provided vehicle's id.
     *
     * @param collectionManager collection manager providing iteration
     * @return summary message with removed count
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        if (vehicleDto == null) {
            return "Error: no vehicle provided.";
        }
        Vehicle target = new VehicleMapper().requestToVehicle(vehicleDto);
        int id = target.getId() == null ? Integer.MAX_VALUE : target.getId();
        int removed = 0;
        var iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            Vehicle v = iterator.next();
            if (v.getId() > id) {
                iterator.remove();
                removed++;
            }
        }
        return removed + " vehicle(s) greater than ID " + id + " have been removed.";
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "remove_greater"}
     */
    @Override
    public String getName() {
        return "remove_greater";
    }
}
