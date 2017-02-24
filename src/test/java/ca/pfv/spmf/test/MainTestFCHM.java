package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoFCHM;
import org.junit.Test;

/**
 * Example of how to use the FCHM algorithm
 * from the source code.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestFCHM {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // input file
            String input = "DB_Utility.txt";
            // output file path
            String output = ".//output.txt";

            // minimum utility treshold
            int min_utility = 30;
            // minimum bond
            double minbond = 0.5; // the minimum bond threhsold


            // Applying the HUIMiner algorithm
            AlgoFCHM algo = new AlgoFCHM();
            algo.runAlgorithm(input, output, min_utility, minbond);
            algo.printStats();
        });
    }
}
