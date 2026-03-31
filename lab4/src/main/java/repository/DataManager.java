package repository;

import common.entity.Vehicle;

import java.util.LinkedHashSet;
import java.util.Set;

public interface DataManager {
    void saveVehicles(Set<Vehicle> vehicles);
    LinkedHashSet<Vehicle> getVehicles();
}
