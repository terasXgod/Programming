package common.commands;

import common.dto.command.CommandPayload;
import common.dto.command.HistoryPayload;
import server.collection.CollectionManager;

import java.util.List;

/**
 * Command to print the history of the last executed commands.
 * History is stored server-side in CollectionManager.
 */
public class HistoryCommand implements Command {

    private List<String> history;

    /**
     * Prints the recent command history supplied from the server.
     *
     * @param collectionManager unused manager reference
     * @return formatted history or empty notice
     */
    @Override
    public String execute(CollectionManager collectionManager) {
        if (history == null || history.isEmpty()) {
            return "Command history is empty.";
        }
        StringBuilder result = new StringBuilder("Command history (last " + history.size() + " commands):\n");
        for (int i = 0; i < history.size(); i++) {
            result.append(i + 1).append(". ").append(history.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns the command keyword.
     *
     * @return {@code "history"}
     */
    @Override
    public String getName() {
        return "history";
    }

    /**
     * Accepts a list of recently executed command names.
     *
     * @param arg argument container carrying history
     */
    @Override
    public void setPayload(CommandPayload payload) throws IllegalArgumentException {
        if (!(payload instanceof HistoryPayload hp)) {
            throw new IllegalArgumentException("Error: invalid history payload.");
        }
        this.history = hp.history();
    }
}
