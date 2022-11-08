import model.DTN.DTN;

import model.STN.STN;
import model.STN.Solution;
import utils.DTN.Generator;
import utils.STN.FloydWarshall;
import utils.Utils;

import java.util.List;

import static utils.Utils.printMatrix;
import static utils.Utils.timeWindows;

public class Main {
    public static void main(String[] args) {
//        STN ex = Generator.exampleSTN();
//        testSTN(ex);


        DTN dtn = Generator.generateDTN(5, 2, 5, 3);

//        System.out.println(Generator.generateDTN(5, 2, 5, 3));
        System.out.println(dtn);
        List<STN> stns = dtn.stnCombinations();

        for (STN stn: stns){
            Solution solution = FloydWarshall.compute(stn);

            printMatrix(solution.getShortestPathsMatrix());

            System.out.println("*********");

            printMatrix(timeWindows(solution));

            boolean res = Utils.isConsistent(solution);

            if (res)
                System.out.println("*** STN  is consistent ***\n");
            System.out.println();
        }


    }

    public static void sprandGenerator(){
        //        boolean res = false;
//        while (!res) {
//            System.out.println("*** NEW SPRAND STN ***");
//
//            STN example = Generator.sprandSTN(10, 0.25);
////            testSTN(example);
//            printMatrix(example.getNetwork());
//
//            System.out.println("*********");
//
//            Solution solution = FloydWarshall.compute(example);
//
//            printMatrix(solution.getShortestPathsMatrix());
//
//            System.out.println("*********");
//
//            printMatrix(timeWindows(solution));
//
//            res = Utils.isConsistent(solution);
//
//            System.out.println("*** END SPRAND STN ***\n");
//
//        }
    }
}