package model;

import java.util.List;

public class DTN {
    private int n;
    private List<DisjunctionConstraint> disjunctionConstraints;

    public DTN(List<DisjunctionConstraint> disjunctionConstraints, int n) {
        this.disjunctionConstraints = disjunctionConstraints;
        this.n = n;
    }

    @Override
    public String toString() {
        return disjunctionConstraints.toString();
    }

    public List<DisjunctionConstraint> getDisjunctionConstraints() {
        return disjunctionConstraints;
    }

    public void setDisjunctionConstraints(List<DisjunctionConstraint> disjunctionConstraints) {
        this.disjunctionConstraints = disjunctionConstraints;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
