package common;

import java.io.Serial;
import java.io.Serializable;

/**
 * Immutable response returned from the server, carrying a message and status flag.
 */
public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final boolean success;

    /**
     * Builds a response with message and success flag.
     *
     * @param message text to show to the client
     * @param success operation success indicator
     */
    public Response(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Returns the response message.
     *
     * @return human-readable message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Indicates whether the command was processed successfully.
     *
     * @return true on success
     */
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "Response{" +  success +  '}';
    }
}
