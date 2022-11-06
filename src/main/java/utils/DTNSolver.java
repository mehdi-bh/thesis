package utils;

import model.DTN;
import model.STN;
import model.Solution;

import java.util.Arrays;

public class DTNSolver {
    public static void bruteForce(DTN dtn) {
        for (STN stn : dtn.getNetwork()) {
            Solution solution = FloydWarshall.compute(stn);
            System.out.println(Arrays.deepToString(Utils.timeWindows(solution)));
        }
    }
}
