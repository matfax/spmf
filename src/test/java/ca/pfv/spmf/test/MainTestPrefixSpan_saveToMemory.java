package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixspan.AlgoPrefixSpan;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixspan.SequentialPattern;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixspan.SequentialPatterns;
import org.junit.Test;

import java.util.List;


/**
 * Example of how to use the PrefixSpan algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestPrefixSpan_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // input file
            String inputFile = "contextPrefixSpan.txt";

            // Create an instance of the algorithm
            AlgoPrefixSpan algo = new AlgoPrefixSpan();
            algo.setMaximumPatternLength(400);

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            algo.setShowSequenceIdentifiers(true);

            // execute the algorithm with minsup = 50 %
            SequentialPatterns patterns = algo.runAlgorithm(inputFile, 0.4, null);
            System.out.println(" == PATTERNS FOUND ==");
            for (List<SequentialPattern> level : patterns.levels) {
                for (SequentialPattern pattern : level) {
                    System.out.println(pattern + " support : " + pattern.getAbsoluteSupport());
                }
            }

            // print statistics
            algo.printStatistics();
        });
    }
}