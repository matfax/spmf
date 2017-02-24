package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPClose;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import org.junit.Test;


/**
 * Example of how to use FPClose from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2015)
 */
public class MainTestFPClose_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the transaction database
            String input = "contextPasquier99.txt"; // the database

            // the minimum support threshold
            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            // Applying the algorithm
            AlgoFPClose algo = new AlgoFPClose();
            // Run the algorithm
            // Note that here we use "null" as output file path because we want to keep the results into memory instead of saving to a file
            Itemsets patterns = algo.runAlgorithm(input, null, minsup);

            // Show the CFI-Tree for debugging!
//		System.out.println(algo.cfiTree);

            // show the execution time and other statistics
            algo.printStats();
            // print the patterns to System.out
            patterns.printItemsets(algo.getDatabaseSize());
        });
    }
}
