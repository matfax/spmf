package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.cfpgrowth.AlgoCFPGrowth;
import org.junit.Test;

/**
 * Example of how to use the CFPGrowth++ algorithm, from the source code.
 */
public class MainTestCFPGrowth_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String database = "contextCFPGrowth.txt";
            String output = ".//output.txt";
            String MISfile = "MIS.txt";

            // Applying the CFPGROWTH algorithmMainTestFPGrowth.java
            AlgoCFPGrowth algo = new AlgoCFPGrowth();
            algo.runAlgorithm(database, output, MISfile);
            algo.printStats();
        });
    }
}
