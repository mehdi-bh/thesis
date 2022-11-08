import model.DTN;
import model.STN;
import model.Solution;
import org.apache.spark.ml.linalg.SparseMatrix;
import utils.DTNSolver;
import utils.FloydWarshall;
import utils.Generator;
import utils.Utils;

import java.util.Random;

import static utils.Utils.*;
import utils.DTN.Generator;

public class Main {
    public static void main(String[] args) {
//        STN ex = Generator.exampleSTN();
//        testSTN(ex);

//        DTN dtn = Generator.exampleDTN();
//        DTNSolver.bruteForce(dtn);

        boolean res = false;
        while (!res){
            System.out.println("*** NEW SPRAND STN ***");

            STN example = Generator.sprandSTN(10,0.25);
//            testSTN(example);
            printMatrix(example.getNetwork());

            System.out.println("*********");

            Solution solution = FloydWarshall.compute(example);

            printMatrix(solution.getShortestPathsMatrix());

            System.out.println("*********");

            printMatrix(timeWindows(solution));

            res = Utils.isConsistent(solution);

            System.out.println("*** END SPRAND STN ***\n");

//        DTN dtn = Generator.exampleDTN();
//        DTNSolver.bruteForce(dtn);

        System.out.println(Generator.generateDTN(5, 2, 5, 3));
    }
}