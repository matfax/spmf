package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoPrefixSpan_with_Strings;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import org.junit.Test;


/**
 * Example of how to use the PrefixSpan algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestPrefixSpan_withString_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpanStrings.txt");
            // print the database to console
//		sequenceDatabase.print();

            // Create an instance of the algorithm with minsup = 50 %
            AlgoPrefixSpan_with_Strings algo = new AlgoPrefixSpan_with_Strings();

            int minsup = 2; // we use a minimum support of 2 sequences.

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase, "sequential_patterns.txt", minsup);
            algo.printStatistics(sequenceDatabase.size());
        });
    }
}