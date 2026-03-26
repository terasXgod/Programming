package common.dto.command;

/**
 * Payload that carries a single identifier.
 */
public record IdPayload(int id) implements CommandPayload {
}

