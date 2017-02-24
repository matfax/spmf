package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixspan.AlgoBIDEPlus;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixspan.SequentialPatterns;
import org.junit.Test;

/**
 * Example of how to use the BIDE+ algorithm, from the source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestBIDEPlus_saveToMemory {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            String inputfile = "contextPrefixSpan.txt";
            // Create an instance of the algorithm
            AlgoBIDEPlus algo = new AlgoBIDEPlus();

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            boolean showSequenceIdentifiers = true;

            // execute the algorithm
            SequentialPatterns patterns = algo.runAlgorithm(inputfile, null, 2);
            patterns.printFrequentPatterns(algo.patternCount, showSequenceIdentifiers);
            algo.printStatistics();
        });
    }
}