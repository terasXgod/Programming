package server;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.*;

/**
 * Configures java.util.logging to use ASCII-only console output with a simple pattern.
 */
public final class LoggingConfig {

    private LoggingConfig() {
    }

    public static void init() {
        Logger root = Logger.getLogger("");
        for (Handler handler : root.getHandlers()) {
            root.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        try {
            consoleHandler.setEncoding(StandardCharsets.US_ASCII.name());
        } catch (Exception ignored) {
            // fallback to default encoding
        }
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                return String.format("%1$tF %1$tT [%2$s] %3$s - %4$s%n",
                        new Date(record.getMillis()),
                        record.getLevel().getName(),
                        record.getLoggerName(),
                        record.getMessage());
            }
        });

        root.addHandler(consoleHandler);
        root.setLevel(Level.INFO);
    }
}
