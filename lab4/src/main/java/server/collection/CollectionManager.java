package server.collection;

import common.model.Vehicle;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Simple container holding the set of vehicles and providing basic
 * operations such as add, remove, and lookup by id.
 */
public class CollectionManager {
    private Set<Vehicle> collection;
    private final LocalDateTime initDate;

    public CollectionManager() {
        collection = new LinkedHashSet<Vehicle>();
        initDate = LocalDateTime.now();
    }

    public CollectionManager(Set<Vehicle> vehicles) {
        collection = vehicles;
        initDate = LocalDateTime.now();
    }

    public void addVehicles(Set<Vehicle> vehicles) {
        collection.addAll(vehicles);
    }

    public void addVehicle(Vehicle vehicle) {
        // set uses equals/hashCode based on id, so duplicates are ignored; we prefer an error
        boolean added = collection.add(vehicle);
        if (!added) {
            throw new IllegalArgumentException("Vehicle with id " + vehicle.getId() + " already exists");
        }
    }

    public Set<Vehicle> getCollection() {
        return collection;
    }

    public Class<?> getCollectionType() {
        return collection.getClass();
    }

    public LocalDateTime getInitDate() {
        return initDate;
    }

    public int getLength() {
        return collection.size();
    }

    public Vehicle getElementById(int id) {
        for (Vehicle v: collection) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public void removeById(int id) {
        collection.removeIf(v -> v.getId().equals(id));
    }

    public String removeVehicle(Vehicle vehicle) {
        collection.remove(vehicle);
        return "Object has been deleted";
    }

    public String clear() {
        collection.clear();
        return "Collection has been cleared";
    }

}
