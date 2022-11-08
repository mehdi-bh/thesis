package model.DTN;

import java.util.List;

public class DisjunctionConstraint {
    List<BinaryConstraint> binaryConstraints;

    public DisjunctionConstraint(List<BinaryConstraint> binaryConstraints) {
        this.binaryConstraints = binaryConstraints;
    }

    public List<BinaryConstraint> getBinaryConstraints() {
        return binaryConstraints;
    }

    public void setBinaryConstraints(List<BinaryConstraint> binaryConstraints) {
        this.binaryConstraints = binaryConstraints;
    }

    @Override
    public String toString() {
        return binaryConstraints.toString();
    }
}
