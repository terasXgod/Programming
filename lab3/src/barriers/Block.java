package barriers;

import utils.RandomGenerator;

public class Block implements Barrier{

    public double getDamage() {
        return RandomGenerator.getDoubleRandom(53., 85.);
    }

    @Override
    public String toString() {
        return "Block";
    }
}
