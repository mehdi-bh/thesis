package model;

public class Solution {
    private double [][] shortestPathsMatrix;

    public Solution(double[][] shortestPathsMatrix) {
        this.shortestPathsMatrix = shortestPathsMatrix;
    }

    public double[][] timeWindows() {
        double[][] matrix = shortestPathsMatrix;
        double[][] timeWindows = new double[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            timeWindows[i][0] = Math.abs(matrix[i][0]);
            timeWindows[i][1] = matrix[0][i];
        }
        return timeWindows;
    }

    public boolean isConsistent(){
        double[][] timeWindows = timeWindows();
        for (double[] tw : timeWindows) {
            if (tw[0] < 0 || tw[1] < 0 || tw[0] > tw[1]) {
                return false;
            }
        }
        return true;
    }

    public double[][] getShortestPathsMatrix() {
        return shortestPathsMatrix;
    }

    public void setShortestPathsMatrix(double[][] shortestPathsMatrix) {
        this.shortestPathsMatrix = shortestPathsMatrix;
    }
}
