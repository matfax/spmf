package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.cmrules.AlgoCMRules;
import org.junit.Test;


/**
 * Example of how to use the CMRULES algorithm from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2010)
 */
public class MainTestCMRULES {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load database

            String input = "contextPrefixSpan.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found
            double minSup = 0.75;
            double minConf = 0.50;

            AlgoCMRules algo = new AlgoCMRules();

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
