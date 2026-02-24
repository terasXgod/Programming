package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import collections.VehicleType;
import collections.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

public class lineParserTest {
    @BeforeEach
    void resetIds() {
        Vehicle.clearIdRegistry();
    }

    @Test
    void lineParserTest1() {
        String line = ",Car1,10,20,2026-02-14T12:00:00,120.5,4,8.5,PLANE";
        Vehicle vehicle = LineParser.parse(line, 1);
        System.out.println(vehicle.getCreationDate());
        assertEquals(1, vehicle.getId());
        assertEquals("Car1", vehicle.getName());
        assertEquals(10, vehicle.getX());
        assertEquals(20, vehicle.getY());
        assertNotNull(vehicle.getCreationDate());
        assertEquals(120.5f, vehicle.getEnginePower());
        assertEquals(4L, vehicle.getNumberOfWheels());
        assertEquals(8.5, vehicle.getFuelConsumption());
        assertEquals(VehicleType.PLANE, vehicle.getType());
    }
    @Test
    void lineParserTest2_shouldFailForMissingRequiredFields() {
        String line = ",,,,,,,,";
        assertThrows(IllegalArgumentException.class, () -> LineParser.parse(line, 1));
    }

    @Test
    void lineParserTest3() {
        String line = ",Plane1,50,100,,123,123,200.0,PLANE";
        Vehicle vehicle = LineParser.parse(line, 1);
        assertEquals(1, vehicle.getId());
        assertEquals("Plane1", vehicle.getName());
        assertEquals(50, vehicle.getX());
        assertEquals(100, vehicle.getY());
        assertNotNull(vehicle.getCreationDate());
        assertEquals(123f, vehicle.getEnginePower());
        assertEquals(123L, vehicle.getNumberOfWheels());
        assertEquals(200.0f, vehicle.getFuelConsumption());
        assertEquals(VehicleType.PLANE, vehicle.getType());
    }

    @Test
    void generatedIdsAreUnique() {
        Vehicle.clearIdRegistry();
        Vehicle v1 = new Vehicle(null, "A", 1, 1, null, 1.0f, null, 1.0, VehicleType.HELICOPTER);
        Vehicle v2 = new Vehicle(null, "B", 2, 2, null, 1.0f, null, 1.0, VehicleType.HELICOPTER);
        assertNotEquals(v1.getId(), v2.getId());
        assertTrue(v1.getId() > 0);
        assertTrue(v2.getId() > 0);
    }

    @Test
    void constructorRejectsDuplicateId() {
        Vehicle.clearIdRegistry();
        Vehicle v1 = new Vehicle(5, "A", 1, 1, null, 1.0f, null, 1.0, VehicleType.HOVERBOARD);
        assertThrows(IllegalArgumentException.class, () ->
                new Vehicle(5, "B", 2, 2, null, 1.0f, null, 1.0, VehicleType.HOVERBOARD));
    }

    @Test
    void constructorRejectsNullNameAndCoordinates() {
        Vehicle.clearIdRegistry();
        assertThrows(IllegalArgumentException.class, () ->
                new Vehicle(null, null, 1, 1, null, 1.0f, null, 1.0, VehicleType.MOTORCYCLE));
        assertThrows(IllegalArgumentException.class, () ->
                new Vehicle(null, "X", null, 1, null, 1.0f, null, 1.0, VehicleType.MOTORCYCLE));
    }
}
