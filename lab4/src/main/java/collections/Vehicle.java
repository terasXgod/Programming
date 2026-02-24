package collections;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDateTime;

public class Vehicle {
    // registry that keeps track of used ids to guarantee uniqueness
    private static final java.util.Set<Integer> usedIds = new java.util.HashSet<>();
    private static final java.util.concurrent.atomic.AtomicInteger idCounter = new java.util.concurrent.atomic.AtomicInteger(1);

    @CsvBindByName(column = "id")
/**
     * Unique identifier; generated automatically and must be positive.
     * Cannot be {@code null}.
     */
    private Integer id;

    @CsvBindByName(column = "name")
/**
     * Name of the vehicle; non-null and non-empty string.
     */
    private String name;

    @CsvBindByName(column = "x")
    private Integer x;

    @CsvBindByName(column = "y")
    private Integer y;

/**
     * Coordinates where the vehicle is located; cannot be {@code null}.
     */
    private Coordinates coordinates;

    @CsvBindByName(column = "creationDate")
/**
     * Timestamp when the vehicle was created; generated automatically and
     * cannot be {@code null}.
     */
    private java.time.LocalDateTime creationDate;

    @CsvBindByName(column = "enginePower")
/**
     * Engine power; must be greater than 0.
     */
    private float enginePower;

    @CsvBindByName(column = "numberOfWheels")
/**
     * Number of wheels; may be {@code null} but if present must be positive.
     */
    private Long numberOfWheels;

    @CsvBindByName(column = "fuelConsumption")
/**
     * Fuel consumption; non-null and greater than zero.
     */
    private Double fuelConsumption;

    @CsvBindByName(column = "type")
/**
     * Type of the vehicle; must not be {@code null}.
     */
    private VehicleType type;

    public Vehicle(Integer id, String name, Integer x, Integer y, LocalDateTime creationDate,
                   float enginePower,  Long numberOfWheels, double fuelConsumption, VehicleType type) {
        // handle id assignment/validation
        if (id == null) {
            this.id = generateId();
        } else {
            if (id <= 0) {
                throw new IllegalArgumentException("id must be positive");
            }
            synchronized (usedIds) {
                if (usedIds.contains(id)) {
                    throw new IllegalArgumentException("Duplicate id: " + id);
                }
                usedIds.add(id);
            }
            this.id = id;
        }

        // name non-null/non-empty
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = name;

        // coordinates must not be null
        if (x == null || y == null) {
            throw new IllegalArgumentException("coordinates x and y cannot be null");
        }
        this.x = x;
        this.y = y;
        setCoordinates(x, y);

        // creation date
        if (creationDate == null) {
            this.creationDate = LocalDateTime.now();
        } else {
            this.creationDate = creationDate;
        }

        // engine power must be >0
        if (enginePower <= 0) {
            throw new IllegalArgumentException("enginePower must be greater than 0");
        }
        this.enginePower = enginePower;

        // numberOfWheels may be null but if present >0
        if (numberOfWheels != null && numberOfWheels <= 0) {
            throw new IllegalArgumentException("numberOfWheels must be positive if provided");
        }
        this.numberOfWheels = numberOfWheels;

        // fuel consumption non-null and >0
        if (fuelConsumption <= 0) {
            throw new IllegalArgumentException("fuelConsumption must be greater than 0");
        }
        this.fuelConsumption = fuelConsumption;

        // type non-null
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        this.type = type;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }
        synchronized (usedIds) {
            if (!this.id.equals(id) && usedIds.contains(id)) {
                throw new IllegalArgumentException("Duplicate id: " + id);
            }
            // update registry
            usedIds.remove(this.id);
            usedIds.add(id);
        }
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getX() { return x; }
    public void setX(Integer x) { this.x = x; }

    public Integer getY() { return y; }
    public void setY(Integer y) { this.y = y; }


    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }
    public void setCoordinates(Integer x, Integer y) { this.coordinates = new Coordinates(x, y); }
    public Coordinates getCoordinates() {
        setCoordinates(x, y);
        return coordinates;
    }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public float getEnginePower() { return enginePower; }
    public void setEnginePower(float enginePower) { this.enginePower = enginePower; }

    public Long getNumberOfWheels() { return numberOfWheels; }
    public void setNumberOfWheels(Long numberOfWheels) { this.numberOfWheels = numberOfWheels; }

    public Double getFuelConsumption() { return fuelConsumption; }
    public void setFuelConsumption(Double fuelConsumption) {
        if (fuelConsumption == null || fuelConsumption <= 0) {
            throw new IllegalArgumentException("fuelConsumption must be non-null and greater than 0");
        }
        this.fuelConsumption = fuelConsumption;
    }

    public VehicleType getType() { return type; }
    public void setType(VehicleType type) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", enginePower=" + enginePower +
                ", numberOfWheels=" + numberOfWheels +
                ", fuelConsumption=" + fuelConsumption +
                ", type=" + type +
                '}';
    }

    public String toSave() {
        return id + "," +
                name + "," +
                x + "," + y + "," +
                creationDate + "," +
                enginePower + "," +
                numberOfWheels + "," +
                fuelConsumption + "," +
                type;
    }

    /**
     * Generates a new unique positive identifier.
     */
    private static synchronized Integer generateId() {
        Integer candidate;
        do {
            candidate = idCounter.getAndIncrement();
        } while (usedIds.contains(candidate));
        usedIds.add(candidate);
        return candidate;
    }

    /**
     * Clears the static id registry and resets the counter. Useful for tests.
     */
    public static synchronized void clearIdRegistry() {
        usedIds.clear();
        idCounter.set(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}