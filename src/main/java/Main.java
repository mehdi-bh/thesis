import algorithms.StnCombinations;
import model.DTN;

import model.STN;
import model.Solution;
import utils.DtnGenerator;
import algorithms.FloydWarshall;

import java.util.List;

import static utils.Utils.printMatrix;

public class Main {
    public static void main(String[] args) {
        DTN dtn = DtnGenerator.generateDTN(5, 2, 15, 3);

        System.out.println(dtn);

        List<STN> STNs = StnCombinations.compute(dtn);

        for (STN stn: STNs){
            System.out.println("*** NEW STN ***");
            System.out.println(stn.getBinaryConstraints());
            printMatrix(stn.getMatrix());

            System.out.println("*********");

            Solution solution = FloydWarshall.compute(stn);

            printMatrix(solution.getShortestPathsMatrix());

            System.out.println("*********");

            printMatrix(solution.timeWindows());

            boolean res = solution.isConsistent();

            if (res){
                System.out.println("*** STN is consistent ***\n");
                return;
            }
            System.out.println();
        }
    }
}