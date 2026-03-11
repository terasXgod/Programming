package common;

import common.model.Vehicle;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String command;
    private String arg;
    private Vehicle vehicle;

    public Request() {}

    public Request(String command) {
        this.command = command;
    }

    public Request(String command, String arg) {
        this.command = command;
        this.arg = arg;
    }

    public Request(String command, Vehicle vehicle) {
        this.command = command;
        this.vehicle = vehicle;
    }

    public Request(String command, String arg, Vehicle vehicle) {
        this.command = command;
        this.arg = arg;
        this.vehicle = vehicle;
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
