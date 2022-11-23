package model;

public class Solution {
    private double [][] shortestPathsMatrix;

    public Solution(double[][] shortestPathsMatrix) {
        this.shortestPathsMatrix = shortestPathsMatrix;
    }

    public double[][] timeWindows(){
        return timeWindows(0);
    }
    public double[][] timeWindows(int source) {
        double[][] matrix = shortestPathsMatrix;
        double[][] timeWindows = new double[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            timeWindows[i][0] = Math.abs(matrix[i][source]);
            timeWindows[i][1] = matrix[source][i];
        }
        return timeWindows;
    }

    public double[][] timeWindowsJobShop() {
        double[][] matrix = shortestPathsMatrix;
        double[][] timeWindows = new double[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            timeWindows[i][1] = Math.abs(matrix[i][0]);
            timeWindows[i][0] = Math.abs(matrix[0][i]);
        }
        return timeWindows;
    }

    public boolean isJobShopConsistent(){
        double[][] timeWindows = timeWindowsJobShop();
        int n = timeWindows.length-1;
        return timeWindows[n][0] <= timeWindows[n][1];
//        return timeWindows[n][0] >= timeWindows[n][1];
    }
    public boolean isConsistent(){
        double[][] timeWindows = timeWindows();
        for (double[] tw : timeWindows) {
            if (tw[0] < 0 || tw[1] < 0 || tw[0] > tw[1] || tw[1] >= Integer.MAX_VALUE || tw[0] >= Integer.MAX_VALUE) {
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
