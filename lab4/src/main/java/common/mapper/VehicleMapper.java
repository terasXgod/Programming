package common.mapper;

import common.dto.model.VehicleDto;
import common.entity.Vehicle;

/**
 * Converts between DTO representations and entity objects.
 */
public class VehicleMapper {

    /**
     * Maps a VehicleDto received from the client into a Vehicle entity instance.
     *
     * @param dto vehicle DTO payload
     * @return new Vehicle entity or null when dto is null
     */
    public Vehicle requestToVehicle(VehicleDto dto) {
        if (dto == null) {
            return null;
        }
        return new Vehicle(
                dto.getName(),
                dto.getCoordinates(),
                dto.getEnginePower(),
                dto.getNumberOfWheels(),
                dto.getFuelConsumption(),
                dto.getType()
        );
    }
}
