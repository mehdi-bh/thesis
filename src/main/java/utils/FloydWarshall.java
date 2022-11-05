package utils;

public class FloydWarshall {
    public static double[][] compute (double[][] distanceMatrix) {
        int n = distanceMatrix.length;

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
        return shortestPathsMatrix;
    }
}
