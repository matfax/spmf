package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoHUGMiner;
import org.junit.Test;

/**
 * Example of how to use the HUG-Miner algorithm
 * from the source code.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTest_HUGMINER_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String input = "DB_Utility.txt";

            int min_utility = 20;

            String output = ".//output.txt";

            // Applying the HUIMiner algorithm
            AlgoHUGMiner hugMiner = new AlgoHUGMiner();
            hugMiner.runAlgorithm(input, output, min_utility);
            hugMiner.printStats();
        });
    }
}
