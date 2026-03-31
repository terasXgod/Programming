package common.commands;

import common.dto.command.CommandPayload;
import common.dto.command.VehiclePayload;
import common.dto.model.VehicleDto;
import common.mapper.VehicleMapper;
import server.collection.CollectionManager;

/**
 * Adds a new vehicle to the collection using data supplied from the client.
 */
public class AddCommand implements Command {

    private VehicleDto vehicleDto;

    /**
     * Converts the incoming DTO to a vehicle and stores it in the collection.
     *
     * @param collectionManager collection manager that persists vehicles
     * @return result message with assigned identifier
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        if (vehicleDto == null) {
            return "Error: no vehicle provided.";
        }
        var vehicle = new VehicleMapper().requestToVehicle(vehicleDto);
        collectionManager.addVehicle(vehicle);
        return "Vehicle with ID " + vehicle.getId() + " has been added.";
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "add"}
     */
    @Override
    public String getName() {
        return "add";
    }

    /**
     * Accepts a vehicle DTO as the command argument.
     *
     * @param payload argument wrapper containing a vehicle
     * @throws IllegalArgumentException when the vehicle argument is missing
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        if (!(payload instanceof VehiclePayload vp) || vp.vehicle() == null) {
            throw new IllegalArgumentException("Error: argument does not contain a valid vehicle.");
        }
        this.vehicleDto = vp.vehicle();
    }
}
