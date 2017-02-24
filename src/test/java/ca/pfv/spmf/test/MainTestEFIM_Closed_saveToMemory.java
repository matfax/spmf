package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.efim_closed.AlgoEFIMClosed;
import ca.pfv.spmf.algorithms.frequentpatterns.efim_closed.Itemsets;
import org.junit.Test;


/**
 * Example of how to run the EFIM-Closed algorithm from the source code, and keep the result in memory
 *
 * @author Philippe Fournier-Viger, 2016
 */
public class MainTestEFIM_Closed_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input and output file paths
            String input = "DB_Utility.txt";

            // the minutil threshold
            int minutil = 30;

            // Run the EFIM algorithm
            AlgoEFIMClosed algo = new AlgoEFIMClosed();
            Itemsets itemsets = algo.runAlgorithm(minutil, input, null, true, Integer.MAX_VALUE, true, true);
            // Print statistics
            algo.printStats();

            // Print the itemsets
            itemsets.printItemsets();
        });
    }
}
