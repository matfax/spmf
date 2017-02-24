package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoFHM_Freq;
import org.junit.Test;

/**
 * Example of how to use the FHM-Freq algorithm
 * from the source code.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestFHMFreq {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            int min_utility = 30;  //
            double minsup = 0.1; // which means 40 % of the database size.

            // Applying the HUIMiner algorithm
            AlgoFHM_Freq fhmfreq = new AlgoFHM_Freq();
            fhmfreq.runAlgorithm(input, output, min_utility, minsup);
            fhmfreq.printStats();

        });
    }
}
