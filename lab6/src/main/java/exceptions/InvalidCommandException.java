package exceptions;

/**
 * Thrown when a requested command name is not recognized or cannot be built.
 */
public class InvalidCommandException extends RuntimeException {
    /**
     * Creates the exception with a descriptive message.
     *
     * @param message details about the invalid command
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
