package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoTSP_nonClosed;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import org.junit.Test;


/**
 * Example of how to use the PrefixSpanWithSupportRising algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestTSP_nonClosed {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            long startTime = System.currentTimeMillis();
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpan.txt");
            System.out.println(System.currentTimeMillis() - startTime + " ms (database load time)");
            // print the database to console
//		sequenceDatabase.print();

            AlgoTSP_nonClosed algo = new AlgoTSP_nonClosed();

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            algo.setShowSequenceIdentifiers(false);

            int k = 2; // we use a k of 2 sequences.

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase, k);
            algo.writeResultTofile("output.txt");
            algo.printStatistics(sequenceDatabase.size());
        });
    }
}