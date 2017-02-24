package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.spam.AlgoVGEN;
import ca.pfv.spmf.algorithms.sequentialpatterns.spam.PatternVGEN;
import org.junit.Test;

import java.util.List;
import java.util.Map;


/**
 * Example of how to use the VGEN algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestVGEN_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            String input = "contextPrefixSpan.txt";
            String output = ".//output.txt";

            // Create an instance of the algorithm
            AlgoVGEN algo = new AlgoVGEN();
//		algo.setMaximumPatternLength(3);

            // This optional parameter allows to specify the maximum pattern length:
//		algo.setMaximumPatternLength(4);  // optional

            // This optional parameter allows to specify the max gap between two
            // itemsets in a pattern. If set to 1, only patterns of contiguous itemsets
            // will be found (no gap).
            //algo.setMaxGap(1);

            // execute the algorithm with minsup = 2 sequences  (50 %)
            List<Map<Integer, List<PatternVGEN>>> generatorPatterns = algo.runAlgorithm(input, output, 0.1);
            algo.printStatistics();

            // PRINT THE PATTTERNS FOUND
            for (Map<Integer, List<PatternVGEN>> map : generatorPatterns) {
                if (map == null) {
                    continue;
                }
                // for each pattern
                for (List<PatternVGEN> patterns : map.values()) {
                    for (PatternVGEN pattern : patterns) {
                        System.out.println(" " + pattern.getPrefix() + "  support : " + pattern.bitmap.getSupport());
                    }
                }
            }
        });
    }
}