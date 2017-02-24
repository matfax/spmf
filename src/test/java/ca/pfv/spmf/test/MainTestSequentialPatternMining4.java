package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.AlgoFournierViger08;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.multidimensionalpatterns.AlgoDim;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.multidimensionalsequentialpatterns.AlgoSeqDim;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.multidimensionalsequentialpatterns.MDSequenceDatabase;
import org.junit.Test;

/**
 * Example of multidimensional sequential pattern mining (example 4).
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestSequentialPatternMining4 {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String input = "ContextMDSequenceNoTime.txt";
            String output = ".//output.txt";

            // Load a sequence database
            MDSequenceDatabase contextMDDatabase = new MDSequenceDatabase(); //
            contextMDDatabase.loadFile(input);
            contextMDDatabase.printDatabase();

            // NOTE ABOUT THE NEXT LINE:
            // If the second boolean is true, the algorithm will use
            // CHARM instead of AprioriClose for mining frequent closed itemsets.
            // This options is offered because on some database, AprioriClose does not
            // perform very well. Other algorithms could be added.
            AlgoDim algoDim = new AlgoDim(true, false); // <-- here

            AlgoSeqDim algoSeqDim2 = new AlgoSeqDim();
            // Minimum absolute support = 50 %
            double minsupp = 0.50;

            // Apply algorithm
            AlgoFournierViger08 algoPrefixSpanHirateClustering
                    = new AlgoFournierViger08(minsupp,
                    0, Long.MAX_VALUE, 0, Long.MAX_VALUE, null, true, true);
            algoSeqDim2.runAlgorithm(contextMDDatabase, algoPrefixSpanHirateClustering, algoDim, true, output);

            // Print results
            algoSeqDim2.printStatistics(contextMDDatabase.size());
            // NOTE : IF YOU DON'T WANT TO MINE *CLOSED* MD-SEQUENCES, JUST CHANGE THE FOUR VALUES "true" for
            // "FALSE" in this example.
        });
    }
}


