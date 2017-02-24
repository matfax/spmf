package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.efim_closed.AlgoEFIMClosed;
import org.junit.Test;


/**
 * Example of how to run the EFIM-Closed algorithm from the source code, and save the result to an output file.
 *
 * @author Philippe Fournier-Viger, 2016
 */
public class MainTestEFIM_Closed_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input and output file paths
            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            // the minutil threshold
            int minutil = 30;

            // Run the EFIM algorithm
            AlgoEFIMClosed algo = new AlgoEFIMClosed();
            algo.runAlgorithm(minutil, input, output, true, Integer.MAX_VALUE, true, true);
            // Print statistics
            algo.printStats();
        });
    }
}
