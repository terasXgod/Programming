package exceptions;

/**
 * Indicates that incoming serialized data could not be converted into a valid request.
 */
public class DeserializationException extends Exception {
    public DeserializationException(String message) {
        super(message);
    }

    public DeserializationException(String message, Throwable cause) {
        super(message, cause);
    }
}

