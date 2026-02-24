package commandHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import collections.CollectionKeeper;
import collections.Vehicle;
import collections.VehicleType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {
    @BeforeEach
    void resetIds() {
        Vehicle.clearIdRegistry();
    }

    @Test
    void groupCountingByCreationDateTest() {
        CollectionKeeper keeper = new CollectionKeeper();
        Vehicle v1 = new Vehicle(1, "A", 0, 0, LocalDateTime.of(2020,1,1,0,0),
                1f, 1L, 1.0, VehicleType.PLANE);
        Vehicle v2 = new Vehicle(2, "B", 0, 0, LocalDateTime.of(2020,1,1,0,0),
                1f, 1L, 1.0, VehicleType.PLANE);
        Vehicle v3 = new Vehicle(3, "C", 0, 0, LocalDateTime.of(2021,1,1,0,0),
                1f, 1L, 1.0, VehicleType.PLANE);
        keeper.addVehicle(v1);
        keeper.addVehicle(v2);
        keeper.addVehicle(v3);

        Manager manager = new Manager(keeper, null);
        String result = manager.groupCountingByCreationDate();
        assertNotNull(result);
        Map<LocalDateTime, Integer> parsed = new HashMap<>();
        for (String line : result.split("\\r?\\n")) {
            if (line.isBlank()) continue;
            String[] parts = line.split(" : ");
            parsed.put(LocalDateTime.parse(parts[0]), Integer.parseInt(parts[1]));
        }

        assertEquals(2, parsed.get(LocalDateTime.of(2020,1,1,0,0)).intValue());
        assertEquals(1, parsed.get(LocalDateTime.of(2021,1,1,0,0)).intValue());
    }
}
