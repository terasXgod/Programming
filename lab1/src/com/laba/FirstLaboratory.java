package com.laba;

public class FirstLaboratory {


    public static void main(String[] args) {
        final int sizeOfS = 8;
        final int sizeOfX = 19;
        
        short[] s = new short[sizeOfS];
        float[] x = new float[sizeOfX];
        double[][] f;

        int ind = 0;
        for (short i = 6; i <= 20; i += 2) {
            s[ind] = i;
            ind++;
        }
        for (int i = 0; i < x.length; i++) {
            x[i] = randomWithBorders(-3.0, 8.0);
        }
        f = createMatrix(s, x, sizeOfS, sizeOfX);
        printMatrix(f);
    }

    public static float randomWithBorders(double start, double stop) {
        return (float) (Math.random() * (stop - start) + start);
    }

    public static float randomWithBorders(float start, float stop) {
        return (float) (Math.random() * (stop - start) + start);
    }

    public static double[][] createMatrix(short[] w, float[] x, int row, int col) {
        double[][] f = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (w[i] == 8) {
                    f[i][j] = Formulas.formula1(x[j]);
                } else if (w[i] == 10
                        || w[i] == 12
                        || w[i] == 18
                        || w[i] == 20) {
                    f[i][j] = Formulas.formula2(x[j]);
                } else {
                    f[i][j] = Formulas.formula3(x[j]);
                }
            }
        }
        return f;
    }

    public static void printMatrix(double[][] f) {
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                System.out.printf(" %- 9.4f", f[i][j]);
            }
            System.out.printf("%n");
        }
    }
}
