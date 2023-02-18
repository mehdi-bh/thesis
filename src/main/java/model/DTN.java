package model;

import constraints.DisjunctionConstraint;

import java.util.List;

public class DTN {
    private int n;
    private List<DisjunctionConstraint> disjunctionConstraints;

    public DTN(int n, List<DisjunctionConstraint> disjunctionConstraints) {
        this.n = n;
        this.disjunctionConstraints = disjunctionConstraints;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (DisjunctionConstraint disjunction : disjunctionConstraints) {
            stringBuilder.append(disjunction.toString()).append("\n");
        }
        return stringBuilder.toString();
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
