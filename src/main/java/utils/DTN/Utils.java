package utils.DTN;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    public static int generateRandomInRange(int lowerBound, int upperBound) {
        return (int) new Random().ints(lowerBound, upperBound)
                .distinct()
                .limit(1)
                .boxed()
                .collect(Collectors.toSet())
                .toArray()[0];
    }

    public static int[] generateTwoRandom(int upperBound) {
        Set<Integer> set = new Random().ints(0, upperBound)
                .distinct()
                .limit(2)
                .boxed()
                .collect(Collectors.toSet());

        return new int[] {(int) set.toArray()[0], (int) set.toArray()[1]};
    }
}
