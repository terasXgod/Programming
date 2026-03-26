package common.dto.model;

import common.entity.Coordinates;
import common.entity.VehicleType;

import java.io.Serial;
import java.io.Serializable;

/**
 * Data transfer object carrying vehicle fields between client and server.
 */
public class VehicleDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private Coordinates coordinates;


    private float enginePower;

    private Long numberOfWheels;

    private Double fuelConsumption;

    private VehicleType type;

    /**
     * Creates a DTO with all required vehicle fields except id.
     *
     * @param name vehicle name
     * @param coordinates coordinates of the vehicle
     * @param enginePower engine power value
     * @param numberOfWheels wheel count
     * @param fuelConsumption fuel consumption value
     * @param type vehicle type
     */
    public VehicleDto(String name, Coordinates coordinates,
                      float enginePower, Long numberOfWheels, double fuelConsumption, VehicleType type) {
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.numberOfWheels = numberOfWheels;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
    }

    /** @return vehicle name */
    public String getName() { return name; }
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


    /**
     * Updates the vehicle name.
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates coordinates.
     *
     * @param coordinates new coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    /**
     * Updates engine power.
     *
     * @param enginePower new power value
     */
    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    /**
     * Updates number of wheels.
     *
     * @param numberOfWheels wheel count
     */
    public void setNumberOfWheels(Long numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    /**
     * Updates fuel consumption.
     *
     * @param fuelConsumption new consumption value
     */
    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * Updates vehicle type.
     *
     * @param type new type
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", enginePower=" + enginePower +
                ", numberOfWheels=" + numberOfWheels +
                ", fuelConsumption=" + fuelConsumption +
                ", type=" + type +
                '}';
    }

    /**
     * Returns CSV-friendly representation of the DTO.
     *
     * @return comma-separated values
     */
    public String toSave() {
        return name + "," + coordinates.getX() + "," + coordinates.getY() + "," +
                 enginePower + "," + numberOfWheels + "," + fuelConsumption + "," + type;
    }
}
