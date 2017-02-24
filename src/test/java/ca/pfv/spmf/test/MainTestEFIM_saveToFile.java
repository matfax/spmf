package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.efim.AlgoEFIM;
import org.junit.Test;


/**
 * Example of how to run the EFIM algorithm from the source code, and save the result to an output file.
 *
 * @author Philippe Fournier-Viger, 2015
 */
public class MainTestEFIM_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input and output file paths
            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            // the minutil threshold
            int minutil = 30;

            // Run the EFIM algorithm
            AlgoEFIM algo = new AlgoEFIM();
            algo.runAlgorithm(minutil, input, output, true, Integer.MAX_VALUE, true);
            // Print statistics
            algo.printStats();


            // if true in next line it will find only closed itemsets, otherwise, all frequent itemsets
            //===================
//		Itemsets itemsets = algo.runAlgorithm(minutil,  input, null, activateMerging, maximumNumberOfTransactions, activateSubTreeUtilityPruning);
//		//==========================
//		itemsets.printItemsets();
        });
    }
}
