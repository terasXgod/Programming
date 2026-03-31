package common.dto.command;

import common.dto.model.VehicleDto;

/**
 * Payload for update command containing target id and replacement vehicle DTO.
 */
public record UpdatePayload(int id, VehicleDto vehicle) implements CommandPayload {
}

