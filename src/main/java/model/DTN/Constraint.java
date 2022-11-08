package model.DTN;

import java.util.List;

public class Constraint {
    List<Disjunct> disjuncts;

    public Constraint(List<Disjunct> disjuncts) {
        this.disjuncts = disjuncts;
    }

    public List<Disjunct> getDisjuncts() {
        return disjuncts;
    }

    public void setDisjuncts(List<Disjunct> disjuncts) {
        this.disjuncts = disjuncts;
    }

    @Override
    public String toString() {
        return disjuncts.toString();
    }
}
