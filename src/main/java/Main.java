import utils.DTN.Generator;

public class Main {
    public static void main(String[] args) {
//        STN example = Generator.exampleSTN();
//        testSTN(example);

//        DTN dtn = Generator.exampleDTN();
//        DTNSolver.bruteForce(dtn);

        System.out.println(Generator.generateDTN(5, 2, 5, 3));
    }
}