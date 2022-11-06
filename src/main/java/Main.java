import model.DTN;
import utils.DTNSolver;
import utils.Generator;

public class Main {
    public static void main(String[] args) {
//        STN example = Generator.exampleSTN();
//        testSTN(example);

        DTN dtn = Generator.exampleDTN();
        DTNSolver.bruteForce(dtn);
    }
}