package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoPrefixSpan_with_Strings;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import org.junit.Test;


/**
 * Example of how to use the Prefixspan algorithms with strings,
 * from source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestPrefixSpan_WithStrings_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpanStrings.txt");
            // print the database to console
            sequenceDatabase.printDatabase();

            // Create an instance of the algorithm with minsup = 50 %
            AlgoPrefixSpan_with_Strings algo = new AlgoPrefixSpan_with_Strings();

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase, null, 2);
            algo.printStatistics(sequenceDatabase.size());
        });
    }
}