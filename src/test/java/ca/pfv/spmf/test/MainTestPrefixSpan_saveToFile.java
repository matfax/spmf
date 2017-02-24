package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixspan.AlgoPrefixSpan;
import org.junit.Test;


/**
 * Example of how to use the PrefixSpan algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestPrefixSpan_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // input file
            String inputFile = "contextPrefixSpan.txt";
            // output file path
            String outputPath = ".//output.txt";

            // Create an instance of the algorithm with minsup = 50 %
            AlgoPrefixSpan algo = new AlgoPrefixSpan();

            int minsup = 2; // we use a minimum support of 2 sequences.

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            algo.setShowSequenceIdentifiers(false);

            // execute the algorithm
            algo.runAlgorithm(inputFile, outputPath, minsup);
            algo.printStatistics();
        });
    }
}