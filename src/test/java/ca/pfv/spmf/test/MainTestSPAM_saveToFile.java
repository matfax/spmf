package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.spam.AlgoSPAM;
import org.junit.Test;


/**
 * Example of how to use the SPAM algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestSPAM_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            String input = "contextPrefixSpan.txt";
            String output = ".//output.txt";

            // Create an instance of the algorithm
            AlgoSPAM algo = new AlgoSPAM();
//		algo.setMinimumPatternLength(3);
//		algo.setMaximumPatternLength(3);

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
//		algo.showSequenceIdentifiersInOutput(true);

            // execute the algorithm with minsup = 2 sequences  (50 %)
            algo.runAlgorithm(input, output, 0.5);
            algo.printStatistics();
        });
    }
}