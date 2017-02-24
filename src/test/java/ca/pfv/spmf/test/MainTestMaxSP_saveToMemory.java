package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoMaxSP;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.SequentialPatterns;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import org.junit.Test;

/**
 * Example of how to use the MaxSP algorithm, from the source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestMaxSP_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpan.txt");
            sequenceDatabase.print();
            // Create an instance of the algorithm
            AlgoMaxSP algo = new AlgoMaxSP();

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            boolean showSequenceIdentifiers = false;

            // execute the algorithm
            SequentialPatterns patterns = algo.runAlgorithm(sequenceDatabase, null, 2);
            algo.printStatistics(sequenceDatabase.size());
            patterns.printFrequentPatterns(sequenceDatabase.size(), showSequenceIdentifiers);
        });
    }
}