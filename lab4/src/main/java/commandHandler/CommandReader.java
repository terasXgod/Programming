package commandHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads commands from a provided {@link Menu} either interactively (stdin)
 * or from a file.  Each line is dispatched back to the menu for execution.
 */
public class CommandReader {
    private Scanner scanner;
    private Menu menu;

    public CommandReader(Menu menu) {
        this.menu = menu;
        this.scanner = new Scanner(System.in);
    }

    /**
     * default constructor; menu can be set later via {@link #setMenu(Menu)}
     */
    public CommandReader() {
        this(null);
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void readFromFile(String fileName) throws FileNotFoundException {
        Scanner oldScanner = scanner;
        scanner = new Scanner(new File(fileName));
        read();
        scanner.close();
        scanner = oldScanner;
    }

    public void read() {
        if (menu == null) {
            throw new IllegalStateException("Menu has not been set for CommandReader");
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            menu.doCommand(line);
        }
    }
}
