package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth_with_strings.AlgoFPGrowth_Strings;
import org.junit.Test;

/**
 * Example of how to use FPGrowth from the source code and saves
 * the result to a file.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestFPGrowth_strings_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the binary context
            String input = "tennis.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            // Applying the FPGROWTH algorithmMainTestFPGrowth.java
            AlgoFPGrowth_Strings algo = new AlgoFPGrowth_Strings();
            algo.runAlgorithm(input, output, minsup);
            algo.printStats();
        });
    }
}
