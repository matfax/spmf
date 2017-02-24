package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoFHN;
import org.junit.Test;

/**
 * Example of how to use the FHN algorithm
 * from the source code.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestFHN_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_NegativeUtility.txt";
            String output = ".//output.txt";

            int min_utility = 80;

            // Applying the FHN algorithm
            AlgoFHN algo = new AlgoFHN();
            algo.runAlgorithm(input, output, min_utility);
            algo.printStats();

        });
    }
}
