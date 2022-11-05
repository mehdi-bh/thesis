import utils.FloydWarshall;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        T0 = Start
        T1 = Wake
        T2 = Car
        T3 = Airport
         */
        int n = 4;
        double[][] stn = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) stn[i][j] = Integer.MAX_VALUE;
            }
        }

        stn[0][1] = 60;
        stn[1][0] = 0;
        stn[1][2] = 40;
        stn[2][1] = -20;
        stn[2][3] = 80;
        stn[3][2] = -40;
        stn[0][3] = 120;
        stn[3][0] = 0;

        printMatrix(stn);

        System.out.println("*********");

        double[][] solution = FloydWarshall.compute(stn);

        printMatrix(solution);

        System.out.println("*********");

        printMatrix(timeWindows(solution));
    }

    public static double[][] timeWindows(double[][] matrix) {
        double[][] timeWindows = new double[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            timeWindows[i][0] = Math.abs(matrix[i][0]);
            timeWindows[i][1] = matrix[0][i];
        }
        return timeWindows;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            System.out.println(Arrays.toString(doubles));
        }
    }
}