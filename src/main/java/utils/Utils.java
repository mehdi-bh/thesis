package utils;

import model.STN.STN;
import model.STN.Solution;
import utils.STN.FloydWarshall;

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

    public static boolean isConsistent(Solution solution){
        double[][] tw = timeWindows(solution);
        for (int i = 0; i < tw.length; i++) {
            if (tw[i][0] < 0 || tw[i][1] < 0 || tw[i][0] > tw[i][1]) return false;

        }
        return true;
    }

    public static void testSTN(STN stn) {
        printMatrix(stn.getNetwork());

        System.out.println("*********");

        Solution solution = FloydWarshall.compute(stn);

        printMatrix(solution.getShortestPathsMatrix());

        System.out.println("*********");

        printMatrix(timeWindows(solution));
    }
}
