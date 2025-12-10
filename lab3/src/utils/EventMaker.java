package utils;

import barriers.*;
import inanimateObjects.Food;

public class EventMaker {

    private static final Barrier[] barriers = {new Pit(), new Block()};
    private static final Food[] foods = {
            new Food("Яблоко", true),
        new Food("Абрикос", true),
        new Food("Пицца", false),
        new Food("Бургер", false),
        new Food("Рыба фугу", false)
};

    private EventMaker(){
    }

    public static Barrier getBarrier() {
        return barriers[RandomGenerator.getIntRandom(0, barriers.length - 1)];
    }

    public static Food getFood() {
        return foods[RandomGenerator.getIntRandom(0, foods.length - 1)];
    }
}
