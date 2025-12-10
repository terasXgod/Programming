package inanimateObjects;

import utils.RandomGenerator;

public class Food {
    protected double benefit;
    protected String name;
    protected boolean quality;

    public Food(String name, boolean quality) {
        this.name = name;
        this.benefit = RandomGenerator.getDoubleRandom(10., 50.);
        if (!quality) {
            this.benefit = benefit * (-1);
        }
        this.quality = quality;
    }

    public Food(String name, double benefit) {
        this.benefit = benefit;
        this.name = name;
        this.quality = (benefit > 0);

    }

    public double getBenefit() {
        return benefit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Food) {
            return ((Food) obj).benefit == benefit;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
