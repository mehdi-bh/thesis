import algorithms.StnCombinations;
import model.*;

import algorithms.FloydWarshall;

import java.util.ArrayList;
import java.util.List;

import static utils.DtnGenerator.feasibleSTNFromJobShopExample;
import static utils.DtnGenerator.jobShopExample;
import static utils.Utils.*;

@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {
//        DTN dtn = DtnGenerator.generateDTN(5, 2, 15, 3);

        // DTN avec l'example jobshop comme j'ai fait dans le bloc notes
        DTN dtn = jobShopExample();
        System.out.println(dtn);
        System.out.println("----------------------------");
        /* STN avec une assignation des contraintes de l'exemple mais c'est une assignation consistente
        donc on peut s'en servir pour trouver ce qui déconne dans notre code
        - Ou alors on peut chercher un autre algo qui check la consistency
        - l'exemple vient d'ici : https://developers.google.com/optimization/scheduling/job_shop
         */
        STN stn = feasibleSTNFromJobShopExample();
        System.out.println(stn);
        printMatrix(stn.getMatrix());
        Solution solution = FloydWarshall.compute(stn);
        printMatrix(solution.getShortestPathsMatrix());
        printMatrix(solution.timeWindows());

//        List<STN> STNs = StnCombinations.compute(dtn);
//
//        for (STN stn: STNs){
//            System.out.println("*** NEW STN ***");
//            System.out.println(stn.getBinaryConstraints());
//            printMatrix(stn.getMatrix());
//
//            System.out.println("*********");
//
//            Solution solution = FloydWarshall.compute(stn);
//
//            printMatrix(solution.getShortestPathsMatrix());
//
//            System.out.println("*********");
//
//            printMatrix(solution.timeWindows());
//
//            boolean res = solution.isConsistent();
//
//            if (res){
//                System.out.println("*** STN is consistent ***\n");
//                return;
//            }
//            System.out.println();
//        }
    }
}