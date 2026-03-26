package server.collection;

import common.entity.Vehicle;

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

    /**
     * Creates an empty collection with initialization timestamp.
     */
    public CollectionManager() {
        collection = new LinkedHashSet<Vehicle>();
        initDate = LocalDateTime.now();
    }

    /**
     * Creates a manager seeded with an existing vehicle set.
     *
     * @param vehicles initial collection contents
     */
    public CollectionManager(Set<Vehicle> vehicles) {
        collection = vehicles;
        initDate = LocalDateTime.now();
    }

    /**
     * Bulk-adds vehicles to the collection.
     *
     * @param vehicles set of vehicles to add
     */
    public void addVehicles(Set<Vehicle> vehicles) {
        collection.addAll(vehicles);
    }

    /**
     * Adds a single vehicle, assigning id and creation date automatically.
     *
     * @param vehicle vehicle to add
     */
    public void addVehicle(Vehicle vehicle) {
        vehicle.setId(generateId());
        vehicle.setCreationDate(LocalDateTime.now());
        boolean added = collection.add(vehicle);
        if (!added) {
            throw new IllegalArgumentException("Vehicle with id " + vehicle.getId() + " already exists");
        }
    }

    /**
     * Returns the underlying collection.
     *
     * @return vehicle set
     */
    public Set<Vehicle> getCollection() {
        return collection;
    }

    /**
     * Returns the concrete collection type.
     *
     * @return collection class
     */
    public Class<?> getCollectionType() {
        return collection.getClass();
    }

    /**
     * Returns the initialization timestamp.
     *
     * @return init date
     */
    public LocalDateTime getInitDate() {
        return initDate;
    }

    /**
     * Returns the number of elements in the collection.
     *
     * @return size
     */
    public int getLength() {
        return collection.size();
    }

    /**
     * Finds a vehicle by its id.
     *
     * @param id identifier to search
     * @return matching vehicle or null
     */
    public Vehicle getElementById(int id) {
        return collection.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Removes a vehicle by id if present.
     *
     * @param id identifier of vehicle to remove
     */
    public void removeById(int id) {
        collection.removeIf(v -> v.getId().equals(id));
    }

    /**
     * Removes the provided vehicle instance.
     *
     * @param vehicle vehicle to remove
     * @return confirmation message
     */
    public String removeVehicle(Vehicle vehicle) {
        collection.remove(vehicle);
        return "Object has been deleted";
    }

    /**
     * Clears the entire collection.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Generates the next unique id based on current contents.
     *
     * @return new id value
     */
    private int generateId() {
        if (collection.isEmpty()) {
            return 1;
        }
        int maxId = collection.stream()
                .mapToInt(Vehicle::getId)
                .max()
                .getAsInt();
        return maxId + 1;
    }

    /**
     * Replaces an existing vehicle preserving its id and creation date.
     *
     * @param id identifier of the vehicle to replace
     * @param newVehicle replacement vehicle data
     */
    public void editVehicle(int id, Vehicle newVehicle) {
        Vehicle existing = getElementById(id);
        if (existing == null) {
            throw new IllegalArgumentException("No vehicle found with ID " + id);
        }
        newVehicle.setId(id);
        newVehicle.setCreationDate(existing.getCreationDate());
        removeById(id);
        addVehicle(newVehicle);
    }
}
