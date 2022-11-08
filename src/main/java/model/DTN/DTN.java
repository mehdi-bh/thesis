package model.DTN;

import java.util.List;

public class DTN {
    private List<Constraint> constraints;

    public DTN(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        return constraints.toString();
    }
}
