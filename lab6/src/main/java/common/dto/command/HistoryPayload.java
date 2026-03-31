package common.dto.command;

import java.util.List;

/**
 * Payload that carries recent command history entries.
 */
public record HistoryPayload(List<String> history) implements CommandPayload {
}

