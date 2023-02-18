import algorithms.FloydWarshall;
import algorithms.StnCombinations;
import constraints.DisjunctionConstraint;
import model.*;
import utils.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Utils.*;

public class RoverDTN {
    public static void main(String[] args) {
        /*
        z = 0
        c = 1
        c' = 2
        x = 3
        x' = 4
        a = 5
        a' = 6
        e = 7
        e' = 8
        d = 9
        d' = 10
        f = 11
        f' = 12
        g = 13
        g' = 14
         */
        List<DisjunctionConstraint> list = new ArrayList<>();

        // z < c
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(1, 0, -480)));

        // duration c
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(1, 2, 90)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(2, 1, -45)));

        // disjunctions : if c < 1h then X otherwise not X
        // 2 1 60 OR 3 4 30 AND 1 2 60 OR 3 4 0
        list.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{2, 3}, new int[]{1, 4}, new int[]{60, 30})));
        list.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{1, 3}, new int[]{2, 4}, new int[]{60, 0})));

        // c < x
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(3, 2, 0)));

        // duration X
        list.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{3, 3}, new int[]{4, 4}, new int[]{30, 0})));
        list.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{4, 4}, new int[]{3, 3}, new int[]{-30, 0})));

//        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(3, 4, 30)));
//        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(4, 3, -30)));

        // x < a
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(5, 4, 0)));

        // duration a
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(5, 6, 90)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(6, 5, -90)));

        // a < e
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(7, 6, 0)));

        // duration e
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(7, 8, 30)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(8, 7, -30)));

        // e < d OR d < e
        list.add(new DisjunctionConstraint(createMultipleBinaryConstraintList(new int[]{9, 7}, new int[]{8, 10}, new int[]{0, 0})));

        // a < d
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(9, 6, 0)));

        // duration d
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(9, 10, 60)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(10, 9, -60)));

        // d < f
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(11, 10, 0)));

        // duration f
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(11, 12, 90)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(12, 11, -90)));

        // f < g
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(13, 12, 0)));

        // duration g
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(13, 14, 30)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(14, 13, -30)));

        // absolute
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(0, 8, 690)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(0, 10, 840)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(0, 14, 900)));

        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(10, 0, -600)));
        list.add(new DisjunctionConstraint(createSingleBinaryConstraintList(14, 0, -840)));

        DTN dtn = new DTN(15, list);

        List<STN> STNs = StnCombinations.compute(dtn);

        for (STN stn : STNs) {
            Solution solution = FloydWarshall.compute(stn);

            if(!solution.isConsistent()) continue;

            double[][] tw = solution.timeWindows();
            System.out.println((Arrays.deepToString(Solution.convertToHours(tw))));

        }
    }
}
