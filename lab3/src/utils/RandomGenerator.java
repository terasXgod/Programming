package utils;

public class RandomGenerator {

    private RandomGenerator() {
    }

    public static double getDoubleRandom(double start, double end) {
        return Math.random() * (end - start) + start;
    }

    public static int getIntRandom(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
