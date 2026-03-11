package common.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    public Integer getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public float getEnginePower() { return enginePower; }
    public Long getNumberOfWheels() { return numberOfWheels; }
    public Double getFuelConsumption() { return fuelConsumption; }
    public VehicleType getType() { return type; }
    public Coordinates getCoordinates() { return coordinates; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    public void setNumberOfWheels(Long numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setType(VehicleType type) {
        this.type = type;
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

    public String toSave() {
        return id + "," + name + "," + coordinates.getX() + "," + coordinates.getY() + "," +
                creationDate + "," + enginePower + "," + numberOfWheels + "," +
                fuelConsumption + "," + type;
    }
}