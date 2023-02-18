package jobshop;

import constraints.BinaryConstraint;
import constraints.DisjunctionConstraint;
import model.DTN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.Utils.createSingleBinaryConstraintList;

public class Utils {
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
            return;
        }

        for (int i = indexDC; i < n; i++) {
            constraints.add(i);
            combinations(n,m,allConstraints, constraints, i+1);
            constraints.remove(constraints.size()-1);
        }
    }
}
