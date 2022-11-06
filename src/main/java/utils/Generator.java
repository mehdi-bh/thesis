package utils;

import model.DTN;
import model.STN;

import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static STN exampleSTN() {
        /*
        T0 = Start
        T1 = Wake
        T2 = Car
        T3 = Airport
         */

        int n = 4;
        double[][] exampleNetwork = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) exampleNetwork[i][j] = Integer.MAX_VALUE;
            }
        }

        exampleNetwork[0][1] = 60;
        exampleNetwork[1][0] = 0;
        exampleNetwork[1][2] = 40;
        exampleNetwork[2][1] = -20;
        exampleNetwork[2][3] = 80;
        exampleNetwork[3][2] = -40;
        exampleNetwork[0][3] = 120;
        exampleNetwork[3][0] = 0;

        return new STN(4, exampleNetwork);
    }

    public static DTN exampleDTN() {
        STN example = exampleSTN();
        double [][] copy = exampleSTN().getNetwork();
        copy[2][3] = 55;
        copy[3][2] = -50;

        List<STN> dtn = new ArrayList<>();
        dtn.add(example);
        dtn.add(new STN(4, copy));

        return new DTN(dtn);
    }
}
