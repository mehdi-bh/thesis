import algorithms.FloydWarshall;
import model.BinaryConstraint;
import model.STN;
import model.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static utils.Utils.*;

public class Test {
    public static void main(String[] args) {
        /*
        z = 0
        c = 1
        c' = 2
        a = 3
        a' = 4
        e = 5
        e' = 6
        d = 7
        d' = 8
        f = 9
        f' = 10
        g = 11
        g' = 12
         */
        List<BinaryConstraint> list = new ArrayList<>();
        list.add(new BinaryConstraint(1, 0, -480));
        list.add(new BinaryConstraint(1, 2, 90));
        list.add(new BinaryConstraint(2, 1, -45));

        list.add(new BinaryConstraint(3, 2, 0));

        list.add(new BinaryConstraint(3, 4, 90));
        list.add(new BinaryConstraint(4, 3, -90));

        list.add(new BinaryConstraint(5, 4, 0));

        list.add(new BinaryConstraint(5, 6, 30));
        list.add(new BinaryConstraint(6, 5, -30));

        list.add(new BinaryConstraint(7, 6, 0));

        list.add(new BinaryConstraint(7, 8, 60));
        list.add(new BinaryConstraint(8, 7, -60));

        list.add(new BinaryConstraint(9, 8, 0));

        list.add(new BinaryConstraint(9, 10, 90));
        list.add(new BinaryConstraint(10, 9, -90));

        list.add(new BinaryConstraint(11, 10, 0));

        list.add(new BinaryConstraint(11, 12, 30));
        list.add(new BinaryConstraint(12, 11, -30));

        list.add(new BinaryConstraint(0, 6, 690));
        list.add(new BinaryConstraint(0, 8, 840));
        list.add(new BinaryConstraint(0, 12, 900));

        list.add(new BinaryConstraint(7, 0, -600));
        list.add(new BinaryConstraint(11, 0, -840));

        STN stn = new STN(13, list);

        printMatrix(stn.getMatrix());

        printMatrix(FloydWarshall.compute(stn).getShortestPathsMatrix());
        printMatrix(FloydWarshall.compute(stn).timeWindows());

    }
}
