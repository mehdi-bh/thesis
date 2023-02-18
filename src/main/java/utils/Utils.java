package utils;

import constraints.BinaryConstraint;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static List<BinaryConstraint> createMultipleBinaryConstraintList(int[] x, int[] y, int[] r) {
        List<BinaryConstraint> binaryConstraints = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            BinaryConstraint binaryConstraint = new BinaryConstraint(x[i], y[i], r[i]);
            binaryConstraints.add(binaryConstraint);
        }
        return binaryConstraints;
    }
    public static List<BinaryConstraint> createSingleBinaryConstraintList(int x, int y, int r) {
        BinaryConstraint binaryConstraint = new BinaryConstraint(x, y, r);
        List<BinaryConstraint> binaryConstraints = new ArrayList<>();
        binaryConstraints.add(binaryConstraint);
        return binaryConstraints;
    }
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append("\n");
        for (double[] line : matrix) {
            stringBuilder.append("[");
            for (double cell : line) {
                String val = cell >= Integer.MAX_VALUE ? "inf, " : cell + ", ";
                stringBuilder.append(val);
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]");
            stringBuilder.append("\n");
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder);
    }
}
