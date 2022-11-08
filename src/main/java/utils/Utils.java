package utils;

import model.STN.Solution;

import java.util.Arrays;

public class Utils {
    public static double[][] timeWindows(Solution solution) {
        double[][] matrix = solution.getShortestPathsMatrix();
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
