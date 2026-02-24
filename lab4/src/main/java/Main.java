import commandHandler.CommandReader;
import commandHandler.Manager;
import commandHandler.Menu;
import data.DataLoader;
import collections.CollectionKeeper;
import collections.Vehicle;

import java.io.IOException;
import java.util.Set;

/**
 * Application entry point.  Loads data, constructs core components and
 * starts the interactive command reader.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Set<Vehicle> vehicles = DataLoader.loadVehicles();
        CollectionKeeper collectionKeeper = new CollectionKeeper(vehicles);

        CommandReader commandReader = new CommandReader();
        Manager manager = new Manager(collectionKeeper, commandReader);
        Menu menu = new Menu(manager);
        commandReader.setMenu(menu);

        commandReader.read();
    }
}
