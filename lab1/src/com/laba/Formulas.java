package com.laba;

public class Formulas {

    public static double sqrtWithBase(double x, int base) {
        return Math.pow(x, 1.0 / base);
    }

    public static double formula1(double x) {
        return 4.0 / sqrtWithBase(2 * x, 3);
    }

    public static double formula2(double x) {
        return Math.sin(Math.pow(Math.pow(x, ((x + 1) / x)), 1.0 / 3.0 / sqrtWithBase(x, 3)));
    }

    public static double formula3(double x) {
        return Math.sin(Math.pow((Math.cos(Math.tan(x))-1)/2/3, 3));
    }
}
