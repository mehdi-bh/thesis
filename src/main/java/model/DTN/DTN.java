package model.DTN;

import model.STN.STN;

import java.util.ArrayList;
import java.util.List;

public class DTN {
    private List<DisjunctionConstraint> disjunctionConstraints;
    private List<List<BinaryConstraint>> stnCombinations;

    public DTN(List<DisjunctionConstraint> disjunctionConstraints) {
        this.disjunctionConstraints = disjunctionConstraints;
    }

    @Override
    public String toString() {
        return disjunctionConstraints.toString();
    }

    public List<STN> stnCombinations() {
        stnCombinations = new ArrayList<>();
        stnCombinations(new ArrayList<>(), 0);

        List<STN> stns = new ArrayList<>();
        for (List<BinaryConstraint> stn : stnCombinations)
            stns.add(new STN(stn));

        return stns;
    }
    private void stnCombinations(List<BinaryConstraint> cur, int indDC) {
        if (cur == null)
            cur = new ArrayList<>();

        if (cur.size() == disjunctionConstraints.size()){
            stnCombinations.add(new ArrayList<>(cur));
            return ;
        }

        for (int i = indDC; i < disjunctionConstraints.size(); i++) {
            List<BinaryConstraint> c = disjunctionConstraints.get(i).getBinaryConstraints();
            for (int j = 0; j < c.size(); j++) {

                cur.add(c.get(j));
                stnCombinations(cur, i+1);
                cur.remove(cur.size()-1);

            }
//            cur.remove(cur.size()-1);

        }

    }
}
