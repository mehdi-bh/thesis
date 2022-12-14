package utils;

import algorithms.FloydWarshall;
import algorithms.StnCombinations;
import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static utils.Utils.*;
import static utils.Utils.createMultipleBinaryConstraintList;

public class DtnGenerator {

    public static DTN generateConsistentDTN(int n, int k, int m, int L){
        DTN dtnConsistent;

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
            dtn = generateDTN(n,k,m,L);
        }

    }
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
        return new DTN(n, dtn);
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

    public static DTN jobShopExample() {
        List<DisjunctionConstraint> disjunctionConstraints = new ArrayList<>();
        //source
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(8, 0, 0)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(8, 3, 0)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(8, 6, 0)));

//        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(0, 8, 0)));
//        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(3, 8, 0)));
//        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(6, 8, 0)));


        //sink
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(2, 9, 0)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(5, 9, 0)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(7, 9, 0)));

//        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(8, 9, 21)));
//        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(9, 8, -21)));

        //precedence
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(0, 1, -3)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(1, 2, -2)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(3, 4, -2)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(4, 5, -1)));
        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(6, 7, -4)));

        //overlap
        disjunctionConstraints.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{0, 3}, new int[]{3, 0}, new int[]{-3, -2})));
        disjunctionConstraints.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{1, 5}, new int[]{5, 1}, new int[]{-2, -4})));
        disjunctionConstraints.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{1, 6}, new int[]{6, 1}, new int[]{-2, -4})));
        disjunctionConstraints.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{5, 6}, new int[]{6, 5}, new int[]{-4, -4})));
        disjunctionConstraints.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{2, 4}, new int[]{4, 2}, new int[]{-2, -1})));
        disjunctionConstraints.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{4, 7}, new int[]{7, 4}, new int[]{-1, -3})));
        disjunctionConstraints.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{2, 7}, new int[]{7, 2}, new int[]{-2, -3})));

        return new DTN(10, disjunctionConstraints);
    }
    public static STN feasibleSTNFromJobShopExample() {
        List<BinaryConstraint> binaryConstraints = new ArrayList<>();

        // Precedence and duration of jobs
        binaryConstraints.add(new BinaryConstraint(0, 1, -3));
//        binaryConstraints.add(new BinaryConstraint(1, 0, 3));
        binaryConstraints.add(new BinaryConstraint(1, 2, -2));
//        binaryConstraints.add(new BinaryConstraint(2, 1, 2));
        binaryConstraints.add(new BinaryConstraint(3, 4, -2));
//        binaryConstraints.add(new BinaryConstraint(4, 3, 2));
        binaryConstraints.add(new BinaryConstraint(4, 5, -1));
//        binaryConstraints.add(new BinaryConstraint(5, 4, 1));
        binaryConstraints.add(new BinaryConstraint(6, 7, -4));
//        binaryConstraints.add(new BinaryConstraint(7, 6, 4));

//        // Source to all starting nodes = 0
        binaryConstraints.add(new BinaryConstraint(8, 0, 0));
        binaryConstraints.add(new BinaryConstraint(8, 3, 0));
        binaryConstraints.add(new BinaryConstraint(8, 6, 0));

//        binaryConstraints.add(new BinaryConstraint(0, 8, 0));
//        binaryConstraints.add(new BinaryConstraint(3, 8, 0));
//        binaryConstraints.add(new BinaryConstraint(6, 8, 0));

//        // End to all starting nodes = 0
//        binaryConstraints.add(new BinaryConstraint(9, 2, 2));
//        binaryConstraints.add(new BinaryConstraint(9, 5, 4));
//        binaryConstraints.add(new BinaryConstraint(9, 7, 3));

        binaryConstraints.add(new BinaryConstraint(2, 9, -2));
        binaryConstraints.add(new BinaryConstraint(5, 9, -4));
        binaryConstraints.add(new BinaryConstraint(7, 9, -3));

//        // Precedence d??but fin
//        binaryConstraints.add(new BinaryConstraint(8, 9, 0));
        binaryConstraints.add(new BinaryConstraint(9, 8, 3));

        // Ordre des jobs dans les machines
        binaryConstraints.add(new BinaryConstraint(3, 0, -2));

        binaryConstraints.add(new BinaryConstraint(5, 1, -4));
        binaryConstraints.add(new BinaryConstraint(6, 5, -4));
        binaryConstraints.add(new BinaryConstraint(6, 1, -4));

        binaryConstraints.add(new BinaryConstraint(4, 2, -1));
        binaryConstraints.add(new BinaryConstraint(4, 7, -1));
        binaryConstraints.add(new BinaryConstraint(7, 2, -3));

        return new STN(10, binaryConstraints);
    }

}
