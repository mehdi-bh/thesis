package utils;

import model.STN;
import model.Solution;
import algorithms.FloydWarshall;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {
    public static int generateRandomInRange(int lowerBound, int upperBound) {
        return (int) new Random().ints(lowerBound, upperBound)
                .distinct()
                .limit(1)
                .boxed()
                .toArray()[0];
    }

    public static int[] generateTwoRandom(int upperBound) {
        List<Integer> list = new Random().ints(0, upperBound)
                .distinct()
                .limit(2)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(list);
        return new int[] {list.get(0), list.get(1)};
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    public static void testSTN(STN stn) {
        printMatrix(stn.getMatrix());

        System.out.println("*********");

        Solution solution = FloydWarshall.compute(stn);

        printMatrix(solution.getShortestPathsMatrix());

        System.out.println("*********");

        printMatrix(solution.timeWindows());
    }
}
