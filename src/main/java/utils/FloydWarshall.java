package utils;

import model.STN;
import model.Solution;

public class FloydWarshall {
    public static Solution compute (STN stn) {
        int n = stn.getN();
        double[][] distanceMatrix = stn.getNetwork();

        double[][] shortestPathsMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(distanceMatrix[i], 0, shortestPathsMatrix[i], 0, n);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double oldPath = shortestPathsMatrix[i][j];
                    double newPath = shortestPathsMatrix[i][k] + shortestPathsMatrix[k][j];
                    shortestPathsMatrix[i][j] = Math.min(oldPath, newPath);
                }
            }
        }
        return new Solution(shortestPathsMatrix);
    }
}
