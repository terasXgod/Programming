package workClasses;

import util.Coordinates;
import util.VehicleType;

public class Vehicle {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float enginePower; //Значение поля должно быть больше 0
    private Long numberOfWheels; //Поле может быть null, Значение поля должно быть больше 0
    private Double fuelConsumption; //Поле не может быть null, Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
}