package common.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Domain entity representing a vehicle stored on the server.
 */
public class Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Coordinates coordinates;

    private LocalDateTime creationDate;

    private float enginePower;

    private Long numberOfWheels;

    private Double fuelConsumption;

    private VehicleType type;

    /**
     * Full constructor including id and creation date (used when loading existing records).
     */
    public Vehicle(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate,
                   float enginePower, Long numberOfWheels, double fuelConsumption, VehicleType type) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.numberOfWheels = numberOfWheels;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
    }

    /**
     * Constructor for new vehicles before id/creationDate are assigned by the server.
     */
    public Vehicle(String name, Coordinates coordinates,
                   float enginePower, Long numberOfWheels, double fuelConsumption, VehicleType type) {
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.numberOfWheels = numberOfWheels;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
    }

    /** @return vehicle id */
    public Integer getId() { return id; }
    /** @return vehicle name */
    public String getName() { return name; }
    /** @return creation timestamp */
    public LocalDateTime getCreationDate() { return creationDate; }
    /** @return engine power */
    public float getEnginePower() { return enginePower; }
    /** @return wheel count */
    public Long getNumberOfWheels() { return numberOfWheels; }
    /** @return fuel consumption */
    public Double getFuelConsumption() { return fuelConsumption; }
    /** @return vehicle type */
    public VehicleType getType() { return type; }
    /** @return coordinates */
    public Coordinates getCoordinates() { return coordinates; }

    /** Sets id (assigned by server). */
    public void setId(Integer id) {
        this.id = id;
    }

    /** Sets name. */
    public void setName(String name) {
        this.name = name;
    }

    /** Sets coordinates. */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /** Sets creation date (used when loading). */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /** Sets engine power. */
    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    /** Sets number of wheels. */
    public void setNumberOfWheels(Long numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    /** Sets fuel consumption. */
    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /** Sets vehicle type. */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * Returns CSV-friendly representation including creation date and id.
     */
    public String toSave() {
        return id + "," + name + "," + coordinates.getX() + "," + coordinates.getY() + "," +
                creationDate + "," + enginePower + "," + numberOfWheels + "," +
                fuelConsumption + "," + type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", enginePower=" + enginePower +
                ", numberOfWheels=" + numberOfWheels +
                ", fuelConsumption=" + fuelConsumption +
                ", type=" + type +
                '}';
    }
}