package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.AlgoPrefixSpanMDSPM;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.multidimensionalpatterns.AlgoDim;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.multidimensionalsequentialpatterns.AlgoSeqDim;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.multidimensionalsequentialpatterns.MDSequenceDatabase;
import org.junit.Test;

/**
 * Example of how to do multi-dimensional sequential pattern
 * mining in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestMultiDimSequentialPatternMining {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Minimum absolute support = 75 %
            double minsupp = 0.75;
            String input = "ContextMDSequenceNoTime.txt";
            String output = ".//output.txt";

            // Load a sequence database
            MDSequenceDatabase contextMDDatabase = new MDSequenceDatabase(); //
            contextMDDatabase.loadFile(input);
//		contextMDDatabase.printContext();

            // If the second boolean is true, the algorithm will use
            // CHARM instead of AprioriClose for mining frequent closed itemsets.
            // This options is offered because on some database, AprioriClose does not
            // perform very well. Other algorithms could be added.
            AlgoDim algoDim = new AlgoDim(false, false);

            AlgoSeqDim algoSeqDim = new AlgoSeqDim();

            // Apply algorithm
            AlgoPrefixSpanMDSPM prefixSpan = new AlgoPrefixSpanMDSPM(minsupp);
            algoSeqDim.runAlgorithm(contextMDDatabase, prefixSpan, algoDim, false, output);

            // Print results
            algoSeqDim.printStatistics(contextMDDatabase.size());
        });
    }
}


