package server.commands;

import java.util.List;

/**
 * Command to print the history of the last executed commands.
 */
public class HistoryCommand implements Command {

    private final List<String> history;

    public HistoryCommand(List<String> history) {
        this.history = history;
    }

    @Override
    public String execute() {
        if (history == null || history.isEmpty()) {
            return "Command history is empty.";
        }

        StringBuilder result = new StringBuilder("Command history (last " + history.size() + " commands):\n");
        for (int i = 0; i < history.size(); i++) {
            result.append((i + 1)).append(". ").append(history.get(i)).append("\n");
        }
        return result.toString();
    }

    @Override
    public String getName() {
        return "history";
    }
}
