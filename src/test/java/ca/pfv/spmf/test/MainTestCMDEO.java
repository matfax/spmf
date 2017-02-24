package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.cmdeogun.AlgoCMDeogun;
import org.junit.Test;

/**
 * Example of how to use the CMDeo algorithm from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2010)
 */
public class MainTestCMDEO {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the database
            String input = "contextPrefixSpan.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found
            double minSup = 0.75;
            double minConf = 0.50;

            AlgoCMDeogun algo = new AlgoCMDeogun();

            // TO SET MINIMUM / MAXIMUM SIZE CONSTRAINTS you can use the following lines:
//		algo.setMinLeftSize(1);
//		algo.setMaxLeftSize(2);
//		algo.setMinRightSize(1);
//		algo.setMaxRightSize(2);

            algo.runAlgorithm(input, output, minSup, minConf);

            algo.printStats();

        });
    }
}
