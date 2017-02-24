package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixspan.AlgoBIDEPlus;
import org.junit.Test;

/*
 * Example of how to use the BIDE+ algorithm, from the source code.
 */
public class MainTestBIDEPlus_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            String inputfile = "contextPrefixSpan.txt";

            int minsup = 2; // we use a minsup of 2 sequences (50 % of the database size)

            AlgoBIDEPlus algo = new AlgoBIDEPlus();  //

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            algo.setShowSequenceIdentifiers(false);

            // execute the algorithm
            algo.runAlgorithm(inputfile, ".//output.txt", minsup);
            algo.printStatistics();
        });
    }
}