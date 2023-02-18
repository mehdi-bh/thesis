package generators;

import org.apache.spark.ml.linalg.SparseMatrix;

import java.util.Random;
import model.STN;

public class StnGenerator {
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
}
