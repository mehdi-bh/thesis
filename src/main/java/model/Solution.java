package model;

public class Solution {
    private double [][] shortestPathsMatrix;

    public Solution(double[][] shortestPathsMatrix) {
        this.shortestPathsMatrix = shortestPathsMatrix;
    }

    public double[][] getShortestPathsMatrix() {
        return shortestPathsMatrix;
    }

    public void setShortestPathsMatrix(double[][] shortestPathsMatrix) {
        this.shortestPathsMatrix = shortestPathsMatrix;
    }
}
