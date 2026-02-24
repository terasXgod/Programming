package commandHandler;

import commandHandler.Commands.Command;
import data.CsvLoader;
import collections.CollectionKeeper;
import collections.Vehicle;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Core application logic.  The {@code Manager} delegates operations on the
 * underlying vehicle collection and provides methods invoked by individual
 * commands.  It also contains helper routines such as help/info/history.
 */
public class Manager {
    private CollectionKeeper collectionKeeper;
    private CommandReader commandReader;

    public Manager(CollectionKeeper collectionKeeper, CommandReader commandReader) {
        this.collectionKeeper = collectionKeeper;
        this.commandReader = commandReader;
    }

    public void setCommandReader(CommandReader reader) {
        this.commandReader = reader;
    }

    public String help() {
        return "    help : вывести справку по доступным командам\n" +
                "    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "    add {element} : добавить новый элемент в коллекцию\n" +
                "    update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "    remove_by_id id : удалить элемент из коллекции по его id\n" +
                "    clear : очистить коллекцию\n" +
                "    save : сохранить коллекцию в файл\n" +
                "    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "    exit : завершить программу (без сохранения в файл)\n" +
                "    remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "    remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "    history : вывести последние 13 команд (без их аргументов)\n" +
                "    group_counting_by_creation_date : сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе\n" +
                "    filter_less_than_fuel_consumption fuelConsumption : вывести элементы, значение поля fuelConsumption которых меньше заданного\n" +
                "    print_field_descending_engine_power : вывести значения поля enginePower всех элементов в порядке убывания";
    }

    public String info() {
        return "Class type: " + collectionKeeper.getCollectionType() +
                ", Initilization Date:" + collectionKeeper.getInitDate() +
                ", Collection size: " + collectionKeeper.getLength() +
                "etc";
    }

    public String show() {
        String inf = "";
        for (Vehicle v : collectionKeeper.getCollection()) {
            inf += v + ",\n";
        }
        return inf;
    }

    public String add(Vehicle vehicle) {
        Set<Vehicle> vehicles = collectionKeeper.getCollection();
        for (Vehicle v : vehicles) {
            if (Objects.equals(v.getId(), vehicle.getId())) {
                return "Vehicle with this id is exist";
            }
        }
        collectionKeeper.addVehicle(vehicle);
        return "Vehicle has completely added";
    }

    public String update(int id, Vehicle vehicle) {
        Vehicle changingVehicle = collectionKeeper.getElementById(id);
        if (Objects.isNull(changingVehicle)) {
            return "[ERROR] There is not Vehicle with id" + id;
        }
        if (Objects.equals(vehicle.getId(), changingVehicle.getId())) {
            changingVehicle=vehicle;
        } else {
            changingVehicle=vehicle;
            changingVehicle.setId(id);
        }
        return "Vehicle with id " + id + " has updated";
    }

    public String removeById(int id) {
        return collectionKeeper.removeById(id);
    }

    public String clear() {
        return collectionKeeper.clear();
    }

    public String save(String fileName) {
        try {
            CsvLoader.load(fileName, collectionKeeper.getCollection());
            return "Data has been completely loaded to " + fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String executeScript(String fileName) {
        if (commandReader == null) {
            return "[ERROR] command reader is not initialized";
        }
        try {
            commandReader.readFromFile(fileName);
            return "Script from " + fileName + " has executed";
        } catch (FileNotFoundException e) {
            return "[ERROR] file not found: " + fileName;
        } catch (Exception e) {
            return "[ERROR] failed to execute script: " + e.getMessage();
        }
    }

    public void exit() {
        System.exit(0);
    }

    public String removeGreater(Vehicle vehicle) {
        int id = vehicle.getId();
        for (Vehicle v: collectionKeeper.getCollection()) {
            if (v.getId() > id) {
                collectionKeeper.removeVehicle(v);
            }
        }
        return "Objects greater then " + vehicle + " has been deleted";
    }

    public String removeLower(Vehicle vehicle) {
        int id = vehicle.getId();
        for (Vehicle v: collectionKeeper.getCollection()) {
            if (v.getId() < id) {
                collectionKeeper.removeVehicle(v);
            }
        }
        return "Objects lower then " + vehicle + " has been deleted";
    }

    public String history() {
        String inf = "";
        for (Command cmd : Menu.logs) {
            inf += "- " + cmd + "\n";
        }
        return inf;
    }

    public String groupCountingByCreationDate() {
        java.util.Map<java.time.LocalDateTime, Integer> counts = new java.util.HashMap<>();
        for (Vehicle v : collectionKeeper.getCollection()) {
            java.time.LocalDateTime date = v.getCreationDate();
            counts.put(date, counts.getOrDefault(date, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (java.util.Map.Entry<java.time.LocalDateTime, Integer> entry : counts.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public String filterLessThanFuelConsumption(Double fuelConsumption) {
        StringBuilder sb = new StringBuilder();
        for (Vehicle v: collectionKeeper.getCollection()) {
            Double fuel = v.getFuelConsumption();
            if (fuel != null && fuel < fuelConsumption) {
                sb.append(v).append("\n");
            }
        }
        return sb.toString();
    }

    public String print_field_descending_engine_power() {
        ArrayList<Float> powers = new ArrayList<>();
        for (Vehicle v: collectionKeeper.getCollection()) {
            powers.add(v.getEnginePower());
        }
        Collections.sort(powers);
        String inf = "";
        for (Float pow : powers) {
            inf += pow + "\n";
        }
        return inf;
    }
}
