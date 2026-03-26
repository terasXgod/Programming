package server.history;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Maintains a bounded history of recently executed command names.
 */
public class HistoryService {
    private static final int HISTORY_LIMIT = 13;
    private final Deque<String> history = new ArrayDeque<>(HISTORY_LIMIT);

    /**
     * Adds a command name to history, evicting the oldest when the limit is exceeded.
     *
     * @param commandName executed command keyword
     */
    public void add(String commandName) {
        if (history.size() >= HISTORY_LIMIT) {
            history.pollFirst();
        }
        history.addLast(commandName);
    }

    /**
     * Returns a snapshot of the recent command history.
     *
     * @return list of command names in execution order
     */
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}
