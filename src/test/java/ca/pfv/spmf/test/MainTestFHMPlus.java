package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoFHMPlus;
import org.junit.Test;

/**
 * Example of how to use the FHM+ algorithm
 * from the source code.
 *
 * @author Philippe Fournier-Viger, 2016
 */
public class MainTestFHMPlus {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            // minimum utility threshold
            int min_utility = 30;

            // minimum and maximum length
            int minimumLength = 2;
            int maximumLength = 3;

            // Applying the algorithm
            AlgoFHMPlus algo = new AlgoFHMPlus();
            algo.runAlgorithm(input, output, min_utility, minimumLength, maximumLength);
            algo.printStats();
        });
    }
}
