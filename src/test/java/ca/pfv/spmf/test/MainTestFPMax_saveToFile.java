package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPMax;
import org.junit.Test;

/**
 * Example of how to use FPMax from the source code and save
 * the resutls to a file.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestFPMax_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // the file paths
            String input = "contextPasquier99.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            // Applying the FPMax algorithm
            AlgoFPMax algo = new AlgoFPMax();
            algo.runAlgorithm(input, output, minsup);
            algo.printStats();
        });
    }
}
