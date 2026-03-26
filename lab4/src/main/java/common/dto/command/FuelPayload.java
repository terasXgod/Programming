package common.dto.command;

/**
 * Payload carrying a fuel consumption threshold.
 */
public record FuelPayload(double fuelConsumption) implements CommandPayload {
}

