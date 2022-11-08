package model.STN;

public class STN {
    private int n;
    private double [][] network;

    public STN(int n, double[][] network) {
        this.n = n;
        this.network = network;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[][] getNetwork() {
        return network;
    }

    public void setNetwork(double[][] network) {
        this.network = network;
    }
}
