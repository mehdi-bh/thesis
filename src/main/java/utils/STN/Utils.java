package utils.STN;

import model.STN.STN;
import model.STN.Solution;

import static utils.Utils.printMatrix;
import static utils.Utils.timeWindows;

public class Utils {
    public static void testSTN(STN stn) {
        printMatrix(stn.getNetwork());

        System.out.println("*********");

        Solution solution = FloydWarshall.compute(stn);

        printMatrix(solution.getShortestPathsMatrix());

        System.out.println("*********");

        printMatrix(timeWindows(solution));
    }
}
