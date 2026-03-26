package common.commands;

import common.dto.command.CommandPayload;
import common.dto.command.UpdatePayload;
import common.dto.model.VehicleDto;
import common.mapper.VehicleMapper;
import server.collection.CollectionManager;
import common.entity.Vehicle;

/**
 * Command to update an existing vehicle identified by id.
 */
public class UpdateCommand implements Command {

    private Integer id;
    private VehicleDto vehicleDto;

    /**
     * Captures both the id to update and the replacement vehicle DTO.
     *
     * @param arg argument container with id and vehicle
     * @throws IllegalArgumentException when either piece is missing
     */
    @Override
    public void setPayload(CommandPayload payload) {
        if (!(payload instanceof UpdatePayload up)) {
            throw new IllegalArgumentException("Error: invalid payload for update.");
        }
        this.id = up.id();
        this.vehicleDto = up.vehicle();
    }

    /**
     * Replaces the vehicle with the given id using data from the DTO.
     *
     * @param collectionManager collection manager used for lookup and update
     * @return result message describing the outcome
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        if (id == null) {
            return "Error: no ID provided.";
        }
        if (vehicleDto == null) {
            return "Error: no vehicle provided.";
        }
        Vehicle existing = collectionManager.getElementById(id);
        if (existing == null) {
            return "Error: no vehicle found with ID " + id + ".";
        }
        var vehicle = new VehicleMapper().requestToVehicle(vehicleDto);
        collectionManager.editVehicle(id, vehicle);
        return "Vehicle with ID " + id + " has been updated.";
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "update"}
     */
    @Override
    public String getName() {
        return "update";
    }
}
