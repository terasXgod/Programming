package common.dto.command;

import common.dto.model.VehicleDto;

/**
 * Payload carrying a single vehicle DTO for commands that operate on one vehicle.
 */
public record VehiclePayload(VehicleDto vehicle) implements CommandPayload {
}

