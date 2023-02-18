import algorithms.FloydWarshall;
import algorithms.StnCombinations;
import generators.DtnGenerator;
import model.*;
import utils.Solution;

import java.io.IOException;
import java.util.List;

import static utils.FileHandling.readFromFile;
import static utils.Utils.printMatrix;

public class GeneratedDTN {
    public static void main(String[] args) throws IOException {
//        DTN dtn = DtnGenerator.generateConsistentDTN(5, 1, 5, 3);
//
//        System.out.println(dtn);
//        printToFile(dtn, "dtnGenerated.txt");

        DTN dtn = readFromFile("dtnGenerated.txt");
        List<STN> STNs = StnCombinations.compute(dtn);

        int nbConsistent = 0;
        for (STN stn: STNs){
            Solution solution = FloydWarshall.compute(stn);
            boolean isConsistent = solution.isConsistent();
            if (isConsistent){
                System.out.println(stn);
                printMatrix(stn.getMatrix());
                printMatrix(solution.getShortestPathsMatrix());
                System.out.println("******** Time Windows ********");
                printMatrix(solution.timeWindows());

                if (++nbConsistent == 3) break;
            }
        }
    }

}
