package model;

import java.util.List;

public class STN {
    private int n;
    private double [][] matrix;
    private List<BinaryConstraint> binaryConstraints;

    public STN(int n, double[][] matrix) {
        this.n = n;
        this.matrix = matrix;
    }

    public STN(int n, List<BinaryConstraint> binaryConstraints){
        this.binaryConstraints = binaryConstraints;
        this.n = n;
        this.matrix = new double[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = new double[n];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matrix[i][j] = Double.MAX_VALUE;
                }
            }
        }

        for (BinaryConstraint bc: binaryConstraints) {
            int x = bc.getX();
            int y = bc.getY();
            int r = bc.getR();

            matrix[x][y] =
                    matrix[x][y] != Double.MAX_VALUE
                    ?
                    Math.min(matrix[x][y], r) : r;
        }
    }

    @Override
    public String toString() {
        return binaryConstraints.toString();
    }

    public List<BinaryConstraint> getBinaryConstraints() {
        return binaryConstraints;
    }

    public void setBinaryConstraints(List<BinaryConstraint> binaryConstraints) {
        this.binaryConstraints = binaryConstraints;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
