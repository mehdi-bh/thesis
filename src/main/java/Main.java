import algorithms.StnCombinations;
import model.*;

import algorithms.FloydWarshall;
import utils.DtnGenerator;
import utils.StnGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.DtnGenerator.feasibleSTNFromJobShopExample;
import static utils.DtnGenerator.jobShopExample;
import static utils.DtnGenerator.generateDTN;
import static utils.Utils.*;

@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {

        //################## DTN Generator ##################
        DTN dtn = DtnGenerator.generateConsistentDTN(5, 2, 15, 3);

        List<STN> STNs = StnCombinations.compute(dtn);

        int nbConsistent = 0;
        for (STN stn: STNs){
            Solution solution = FloydWarshall.compute(stn);
            boolean isConsistent = solution.isConsistent();
            if (isConsistent){
                printMatrix(solution.getShortestPathsMatrix());
                System.out.println("******** Time Windows ********");
                printMatrix(solution.timeWindows());
                if (++nbConsistent == 3) break;
            }
        }

        //################## Example STN ##################
        STN exampleSTN = StnGenerator.exampleSTN();
        printMatrix(exampleSTN.getMatrix());
        Solution solution = FloydWarshall.compute(exampleSTN);
        System.out.println("******** Shortest Path Matrix ********");
        printMatrix(solution.getShortestPathsMatrix());
        System.out.println("******** Time Windows ********");
        printMatrix(solution.timeWindows());


        //################## Modeling Job Shop in DTN ####################
        final List<List<Task>> allJobs =
                Arrays.asList(
                        Arrays.asList(new Task(0, 3), new Task(1, 2), new Task(2, 2)), // Job0
                        Arrays.asList(new Task(0, 2), new Task(2, 1), new Task(1, 4)), // Job1
                        Arrays.asList(new Task(1, 4), new Task(2, 3)) // Job2
                );

        JobShop jobShop = new JobShop(allJobs, 3, 8, 3);
        resolveJobShop(jobShop,12);
//        DTN dtnFromJobShop = jobShopToDTN(jobShop,12);
    }

    public static void resolveJobShop(JobShop jobShop, int limit) {
        DTN dtnFromJobShop = jobShopToDTN(jobShop, limit);
        List<STN> STNs = StnCombinations.compute(dtnFromJobShop);

        double best = Integer.MAX_VALUE;
        for (STN stn : STNs) {

            Solution jobShopStnSolution = FloydWarshall.compute(stn);

            if(!jobShopStnSolution.isJobShopConsistent()) continue;

            double[][] tw = jobShopStnSolution.timeWindowsJobShop();

            if(tw[tw.length-1][0] <= best){
                System.out.println("New best solution found");
                best = tw[tw.length-1][0];
                printMatrix(tw);
            }
        }
    }

//    public static void sprandGenerator(){
//        //        boolean res = false;
////        while (!res) {
////            System.out.println("*** NEW SPRAND STN ***");
////
////            STN example = Generator.sprandSTN(10, 0.25);
//////            testSTN(example);
////            printMatrix(example.getNetwork());
////
////            System.out.println("*********");
////
////            Solution solution = FloydWarshall.compute(example);
////
////            printMatrix(solution.getShortestPathsMatrix());
////
////            System.out.println("*********");
////
////            printMatrix(timeWindows(solution));
////
////            res = Utils.isConsistent(solution);
////
////            System.out.println("*** END SPRAND STN ***\n");
////
////        }
//    }

//    public void backup(){
//        // DTN avec l'example jobshop comme j'ai fait dans le bloc notes
////        DTN dtn = jobShopExample();
////
////        System.out.println(dtn);
////        System.out.println("----------------------------");
////
////        STN stnJobShop = feasibleSTNFromJobShopExample();
//////        STN stn = StnGenerator.exampleSTN();
////        System.out.println(stnJobShop);
////        printMatrix(stnJobShop.getMatrix());
////        Solution solution = FloydWarshall.compute(stnJobShop);
////        printMatrix(solution.getShortestPathsMatrix());
////        printMatrix(solution.timeWindows(8));
//
////        List<STN> STNs = StnCombinations.compute(dtnFromJobShop);
//
////        for (STN stn: STNs){
////            System.out.println("*** NEW STN ***");
////            System.out.println(stn.getBinaryConstraints());
////
////            System.out.println("*********");
////
////            Solution jobShopStnSolution = FloydWarshall.compute(stn);
////
////            printMatrix(jobShopStnSolution.getShortestPathsMatrix());
////
////            System.out.println("*********");
////
////            printMatrix(jobShopStnSolution.timeWindowsJobShop());
////
////            boolean res = jobShopStnSolution.isJobShopConsistent();
////
////            if (res){
////                System.out.println("*** STN is consistent ***\n");
////                return;
////            }
////
////            if (Arrays.deepEquals(jobShopStnSolution.timeWindows(0),solution.timeWindows(8))){
////                System.out.println("*** Similar STN deepEquals to hardcoded one found ***");
////                return;
////            }
////
////            System.out.println();
////        }
//    }
}