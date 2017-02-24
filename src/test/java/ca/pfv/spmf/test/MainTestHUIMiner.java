package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoHUIMiner;
import org.junit.Test;

/**
 * Example of how to use the HUIMiner algorithm
 * from the source code.
 *
 * @author Philippe Fournier-Viger, 2010
 */
public class MainTestHUIMiner {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            int min_utility = 30;  //

            // Applying the HUIMiner algorithm
            AlgoHUIMiner huiminer = new AlgoHUIMiner();
            huiminer.runAlgorithm(input, output, min_utility);
            huiminer.printStats();

        });
    }
}
