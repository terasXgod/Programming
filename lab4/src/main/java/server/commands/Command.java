package server.commands;

import common.model.Vehicle;

public interface Command {

    String execute();

    String getName();

    default void setArg(String arg) {
        // no-op by default
    }

    default void setVehicle(Vehicle vehicle) {
        // no-op by default
    }
}
