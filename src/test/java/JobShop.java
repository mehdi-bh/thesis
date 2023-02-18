import algorithms.StnCombinations;
import model.*;

import algorithms.FloydWarshall;
import utils.Solution;

import java.util.List;

import static jobshop.Utils.jobShopToDTN;
import static utils.Utils.*;

@SuppressWarnings("ALL")
public class JobShop {
    public static void main(String[] args) {

        //################## Example STN ##################
//        STN exampleSTN = StnGenerator.exampleSTN();
//        printMatrix(exampleSTN.getMatrix());
//        Solution solution = FloydWarshall.compute(exampleSTN);
//        System.out.println("******** Shortest Path Matrix ********");
//        printMatrix(solution.getShortestPathsMatrix());
//        System.out.println("******** Time Windows ********");
//        printMatrix(solution.timeWindows());


        //################## Modeling Job Shop in DTN ####################
//        final List<List<Task>> allJobs =
//                Arrays.asList(
//                        Arrays.asList(new Task(0, 3), new Task(1, 2), new Task(2, 2)), // Job0
//                        Arrays.asList(new Task(0, 2), new Task(2, 1), new Task(1, 4)), // Job1
//                        Arrays.asList(new Task(1, 4), new Task(2, 3)) // Job2
//                );
//
//        JobShop jobShop = new JobShop(allJobs, 3, 8, 3);
//        resolveJobShop(jobShop,12);
//        DTN dtnFromJobShop = jobShopToDTN(jobShop,12);
    }

    public static void resolveJobShop(jobshop.JobShop jobShop, int limit) {
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