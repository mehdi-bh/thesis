package algorithms;

import model.BinaryConstraint;
import model.DTN;
import model.DisjunctionConstraint;
import model.STN;

import java.util.ArrayList;
import java.util.List;

public class StnCombinations {
    private static List<List<BinaryConstraint>> stnCombinations;
    private static List<DisjunctionConstraint> disjunctionConstraints;

    public static List<STN> compute(DTN dtn) {
        stnCombinations = new ArrayList<>();
        disjunctionConstraints = dtn.getDisjunctionConstraints();

        stnCombinations(new ArrayList<>(), 0);

        List<STN> STNs = new ArrayList<>();
        for (List<BinaryConstraint> stn : stnCombinations) {
            STNs.add(new STN(dtn.getN(), stn));
        }

        return STNs;
    }
    private static void stnCombinations(List<BinaryConstraint> currentStn, int indexDC) {
        if (currentStn == null) {
            currentStn = new ArrayList<>();
        }

        if (currentStn.size() == disjunctionConstraints.size()) {
            stnCombinations.add(new ArrayList<>(currentStn));
            return;
        }

        for (int i = indexDC; i < disjunctionConstraints.size(); i++) {
            List<BinaryConstraint> c = disjunctionConstraints.get(i).getBinaryConstraints();
            for (BinaryConstraint binaryConstraint : c) {
                currentStn.add(binaryConstraint);
                stnCombinations(currentStn, i + 1);
                currentStn.remove(currentStn.size() - 1);
            }
        }
    }
}
