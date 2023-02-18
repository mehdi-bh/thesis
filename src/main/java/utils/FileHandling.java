package utils;

import constraints.BinaryConstraint;
import constraints.DisjunctionConstraint;
import model.DTN;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandling {
    public static void printToFile(DTN dtn, String fileName) throws IOException {
        try {
            FileWriter file = new FileWriter(fileName);

            file.write(dtn.getN() + "\n");
            file.write(dtn.getDisjunctionConstraints().size() + "\n");
            for (DisjunctionConstraint disjunction : dtn.getDisjunctionConstraints()) {
                file.write(disjunction.getBinaryConstraints().size() + "\n");
                for (BinaryConstraint constraint : disjunction.getBinaryConstraints()) {
                    file.write(constraint.getX() + " " + constraint.getY() + " " + constraint.getR() + "\n");
                }
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println("Error while writing in file");
        }
    }

    public static DTN readFromFile(String fileName) {
        DTN dtn = null;
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt();
            int c = scanner.nextInt();

            List<DisjunctionConstraint> disjunctionConstraints = new ArrayList<>();
            for (int i = 0; i < c; i++) {
                int nBinary = scanner.nextInt();
                List<BinaryConstraint> binaryConstraints = new ArrayList<>();
                for (int j = 0; j < nBinary; j++) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    int r = scanner.nextInt();
                    binaryConstraints.add(new BinaryConstraint(x, y, r));
                }
                disjunctionConstraints.add(new DisjunctionConstraint(binaryConstraints));
            }

            dtn = new DTN(n, disjunctionConstraints);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return dtn;
    }
}
