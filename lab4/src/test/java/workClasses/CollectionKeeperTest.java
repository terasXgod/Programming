package workClasses;

import collections.CollectionKeeper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import collections.VehicleType;
import collections.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionKeeperTest {
    @BeforeEach
    void resetIds() {
        Vehicle.clearIdRegistry();
    }

    @Test
    void constructorDuplicateIdThrows() {
        // first one succeeds
        Vehicle v1 = new Vehicle(1, "A", 0, 0, null, 1.0f, null, 1.0, VehicleType.SPACESHIP);
        // second construction with same id should fail before even adding to any keeper
        assertThrows(IllegalArgumentException.class, () ->
                new Vehicle(1, "B", 1, 1, null, 1.0f, null, 1.0, VehicleType.SPACESHIP));
    }

    @Test
    void addingSameInstanceTwiceThrows() {
        CollectionKeeper keeper = new CollectionKeeper();
        Vehicle v1 = new Vehicle(2, "A", 0, 0, null, 1.0f, null, 1.0, VehicleType.MOTORCYCLE);
        keeper.addVehicle(v1);
        // adding the same object reference again should trigger our duplicate-id error
        assertThrows(IllegalArgumentException.class, () -> keeper.addVehicle(v1));
    }
}