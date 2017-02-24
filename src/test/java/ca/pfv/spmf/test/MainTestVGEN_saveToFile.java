package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.spam.AlgoVGEN;
import org.junit.Test;


/**
 * Example of how to use the VGEN algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestVGEN_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            String input = "contextPrefixSpan.txt";
            String output = ".//output.txt";

            // Create an instance of the algorithm
            AlgoVGEN algo = new AlgoVGEN();

            // This optional parameter allows to specify the maximum pattern length:
//		algo.setMaximumPatternLength(4);  // optional

            // This optional parameter allows to specify the max gap between two
            // itemsets in a pattern. If set to 1, only patterns of contiguous itemsets
            // will be found (no gap).
            //algo.setMaxGap(1);

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
//		algo.showSequenceIdentifiersInOutput(true);

            // execute the algorithm with minsup = 2 sequences  (50 %)
            algo.runAlgorithm(input, output, 0.5);
            algo.printStatistics();
        });
    }
}