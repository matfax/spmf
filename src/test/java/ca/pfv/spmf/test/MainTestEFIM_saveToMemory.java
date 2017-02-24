package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.efim.AlgoEFIM;
import ca.pfv.spmf.algorithms.frequentpatterns.efim.Itemsets;
import org.junit.Test;


/**
 * Example of how to run the EFIM algorithm from the source code, and keep the result in memory
 *
 * @author Philippe Fournier-Viger, 2015
 */
public class MainTestEFIM_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input and output file paths
            String input = "DB_Utility.txt";

            // the minutil threshold
            int minutil = 30;

            // Run the EFIM algorithm
            AlgoEFIM algo = new AlgoEFIM();
            Itemsets itemsets = algo.runAlgorithm(minutil, input, null, true, Integer.MAX_VALUE, true);
            // Print statistics
            algo.printStats();

            // Print the itemsets
            itemsets.printItemsets();
        });
    }
}
