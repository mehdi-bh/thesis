package utils;

import org.apache.spark.ml.linalg.SparseMatrix;

import java.util.Random;
import model.STN;

public class StnGenerator {

    public static STN sprandSTN(int n, double d) {
        SparseMatrix sm = SparseMatrix.sprand(n,n,d,new Random(5));

        int[] colPtrs = sm.colPtrs();
        int[] rowInd = sm.rowIndices();
        double[] values = sm.values();

        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++)
            matrix[i] = new double[n];

        int col = 0;
        //Decompress matrix (Sprand generate compressed format since it's sparse)
        for (int i = 0; i < values.length; i++) {
            if (colPtrs[col+1] == i) col++;
            matrix[rowInd[i]][col] = values[i];
        }

        Random rd = new Random();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j){
                    if (matrix[i][j] == 0) matrix[i][j] = Integer.MAX_VALUE;
                    //add 50% of negative edges
                    else matrix[i][j] *= (rd.nextBoolean() ? 1 : -1);
                }
                else matrix[i][j] = 0;
            }
        }

        return new STN(n,matrix);
    }
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
        exampleNetwork[2][1] = -30;
        exampleNetwork[2][3] = 70;
        exampleNetwork[3][2] = -40;
        exampleNetwork[0][3] = 70;
        exampleNetwork[3][0] = 0;

        return new STN(4, exampleNetwork);
    }

//    public static DTN exampleDTN() {
//        STN example = exampleSTN();
//        double [][] copy = exampleSTN().getNetwork();
//        copy[2][3] = 55;
//        copy[3][2] = -50;
//
//        List<STN> dtn = new ArrayList<>();
//        dtn.add(example);
//        dtn.add(new STN(4, copy));
//
//        return new DTN(dtn);
//    }
}
