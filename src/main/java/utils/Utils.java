package utils;

import model.*;
import algorithms.FloydWarshall;

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

    public static DTN jobShopToDTN(JobShop jobShop){
        return jobShopToDTN(jobShop, -1);
    }
    public static DTN jobShopToDTN(JobShop jobShop, int limit){
        List<DisjunctionConstraint> disjunctionConstraints = new ArrayList<>();
        int horizon = 0;

        for (int i = 0; i < jobShop.getTasks().size() ; i++) {
            List<Task> tasks = jobShop.getTasks().get(i);
            disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(0, tasks.get(0).getId(), 0)));
            disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList( tasks.get(tasks.size()-1).getId(),jobShop.getNbTasks() + 1, -tasks.get(tasks.size()-1).getDur())));

            for (int j = 0; j < tasks.size()-1; j++) {
                Task t = tasks.get(j);
                disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(t.getId(), tasks.get(j+1).getId(), -t.getDur())));
                horizon += t.getDur();
            }
            horizon += tasks.get(tasks.size()-1).getDur();
        }

        for(Map.Entry<Integer,List<Task>> entry :jobShop.getTaskByMachine().entrySet()){
            List<List<Integer>> combinationsIndices = new ArrayList<>();
            combinations(entry.getValue().size(), 2, combinationsIndices, new ArrayList<>(),0);

            for (List<Integer> disjunctIndices: combinationsIndices){
                disjunctionConstraints.add(tasksToDisjunction(
                        entry.getValue().get(disjunctIndices.get(0)),
                        entry.getValue().get(disjunctIndices.get(1))
                    )
                );
            }
        }

        disjunctionConstraints.add(new DisjunctionConstraint(createSingleBinaryConstraintList(jobShop.getNbTasks() + 1, 0, limit == -1 ? horizon : limit)));

//        for(DisjunctionConstraint dc: disjunctionConstraints)
//            for(BinaryConstraint bc: dc.getBinaryConstraints())
//                bc.setR(-bc.getR());

        return new DTN(jobShop.getNbTasks() + 2 , disjunctionConstraints);
    }

    private static DisjunctionConstraint tasksToDisjunction(Task t1, Task t2){
        return new DisjunctionConstraint(List.of(
                new BinaryConstraint(t1.getId(), t2.getId(),-t1.getDur()),
                new BinaryConstraint(t2.getId(), t1.getId(),-t2.getDur())
                ));
    }

    public static void combinations(int n, int m, List<List<Integer>> allConstraints, List<Integer> constraints, int indexDC) {
        if (constraints == null) {
            constraints = new ArrayList<>();
        }

        if (constraints.size() == m) {
            allConstraints.add(new ArrayList<>(constraints));
//            stnCombinations.add(new ArrayList<>(currentStn));
            return;
        }

        for (int i = indexDC; i < n; i++) {
            constraints.add(i);
            combinations(n,m,allConstraints, constraints, i+1);
            constraints.remove(constraints.size()-1);
        }
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
//            stringBuilder.append(Arrays.toString(line));
//            stringBuilder.append("\n");
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder);
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
