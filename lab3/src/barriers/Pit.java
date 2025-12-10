package barriers;

import inanimateObjects.Car;
import utils.RandomGenerator;

public class Pit implements Barrier{

    public double getDamage() {
        return RandomGenerator.getDoubleRandom(10., 32.);
    }

    @Override
    public String toString() {
        return "Pit";
    }
}
