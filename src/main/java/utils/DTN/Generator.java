package utils.DTN;

import model.DTN.DisjunctionConstraint;
import model.DTN.DTN;
import model.DTN.BinaryConstraint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static utils.DTN.Utils.generateRandomInRange;
import static utils.DTN.Utils.generateTwoRandom;

public class Generator {
    /*
    n = number of variables
    k = number of disjunct per constraint
    m = number of constraints
    L = maximum integer value
     */
    public static DTN generateDTN(int n, int k, int m, int L) {
        List<DisjunctionConstraint> dtn = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            HashSet<BinaryConstraint> constraint = new HashSet<>();
            while (constraint.size() < k) {
                constraint.add(generateDisjunct(n, L));
            }

            dtn.add(new DisjunctionConstraint(new ArrayList<>(constraint)));
        }
        return new DTN(dtn);
    }

    public static BinaryConstraint generateDisjunct(int n, int L){
        // Select two random variables, make sure they are different
        int[] variables = generateTwoRandom(n);

        // Select integer in range [O,L] with 50% chance
        int r = generateRandomInRange(-L, L);

        int x = variables[0];
        int y = variables[1];

        return new BinaryConstraint(x, y, r);
    }
}
