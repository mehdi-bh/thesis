package model.STN;

import model.DTN.BinaryConstraint;

import java.util.List;

public class STN {
    private int n;
    private double [][] network;

    private List<BinaryConstraint> bcs;

    public STN(int n, double[][] network) {
        this.n = n;
        this.network = network;
    }

    public STN(List<BinaryConstraint> bcs, int n){
        this.bcs = bcs;
        this.n = n;
        this.network = new double[n][];

        for (int i = 0; i < n; i++)
            network[i] = new double[n];

        for (BinaryConstraint bs: bcs){
            if (network[bs.getX()][bs.getY()] != 0)
                network[bs.getX()][bs.getY()] = Math.min(network[bs.getX()][bs.getY()],bs.getR());
            else network[bs.getX()][bs.getY()] = bs.getR();
        }

    }

    public List<BinaryConstraint> getBcs() {
        return bcs;
    }

    public void setBcs(List<BinaryConstraint> bcs) {
        this.bcs = bcs;
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
