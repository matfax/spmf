package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.lapin.AlgoLAPIN_LCI;
import org.junit.Test;

/**
 * Example of how to use the LAPIN_LCI (a.k.a LAPIN-SPAM) algorithm in source code.
 *
 * @author Philippe Fournier-Viger 2014
 */
public class MainTestLAPIN_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String inputPath = "contextPrefixSpan.txt";
            String outputPath = ".//output.txt";

            // Create an instance of the algorithm with minsup = 50 %
            AlgoLAPIN_LCI algo = new AlgoLAPIN_LCI();

            double minsup = 0.2; // we use a minimum support of 2 sequences.

            // execute the algorithm
            algo.runAlgorithm(inputPath, outputPath, minsup);
            algo.printStatistics();
        });
    }
}