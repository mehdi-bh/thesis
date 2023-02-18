package generators;

import algorithms.FloydWarshall;
import algorithms.StnCombinations;
import constraints.BinaryConstraint;
import constraints.DisjunctionConstraint;
import model.*;
import utils.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static utils.Utils.*;
import static utils.Utils.createMultipleBinaryConstraintList;

public class DtnGenerator {

    public static DTN generateConsistentDTN(int n, int k, int m, int L){
        DTN dtn = generateDTN(n,k,m,L);
        boolean isConsistent = false;

        while(true){
            List<STN> STNs = StnCombinations.compute(dtn);

            for (STN stn: STNs){
                Solution solution = FloydWarshall.compute(stn);

                isConsistent = solution.isConsistent();
                if (isConsistent){
                    return dtn;
                }
            }
            dtn = generateDTN(n, k, m, L);
        }
    }

    /*
    n = number of variables
    k = number of disjunct per constraint
    m = number of constraints
    L = maximum integer value
     */
    private static DTN generateDTN(int n, int k, int m, int L) {
        List<DisjunctionConstraint> dtn = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            HashSet<BinaryConstraint> constraint = new HashSet<>();
            while (constraint.size() < k) {
                constraint.add(generateDisjunct(n, L));
            }

            dtn.add(new DisjunctionConstraint(new ArrayList<>(constraint)));
        }
        return new DTN(n, dtn);
    }

    private static BinaryConstraint generateDisjunct(int n, int L){
        // Select two random variables, make sure they are different
        int[] variables = generateTwoRandom(n);

        // Select integer in range [O,L] with 50% chance
        int r = generateRandomInRange(-L, L + 1);

        int x = variables[0];
        int y = variables[1];

        return new BinaryConstraint(x, y, r);
    }
}
