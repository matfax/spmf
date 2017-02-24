package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.cfpgrowth.AlgoCFPGrowth;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import org.junit.Test;

/**
 * Example of how to use the CFPGrowth++ algorithm, from the source code and save the result to
 * memory instead of into a file.
 */
public class MainTestCFPGrowth_saveToMemory {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String database = "contextCFPGrowth.txt";
            String output = null;  // because we want to indicate
            // that we want to keep the result into memory instead of
            // saving it to a file
            String MISfile = "MIS.txt";

            // Applying the CFPGROWTH algorithmMainTestFPGrowth.java
            AlgoCFPGrowth algo = new AlgoCFPGrowth();
            Itemsets result = algo.runAlgorithm(database, output, MISfile);
            algo.printStats();

            result.printItemsets(algo.getDatabaseSize());
        });
    }
}
