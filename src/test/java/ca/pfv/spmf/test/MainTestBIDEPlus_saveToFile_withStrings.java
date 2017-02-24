package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoBIDEPlus_withStrings;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import org.junit.Test;

/**
 * Example of how to use the BIDE+ algorithm with strings, from
 * the source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestBIDEPlus_saveToFile_withStrings {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpanStrings.txt");
            sequenceDatabase.printDatabase();

            int minsup = 2; // we use a minsup of 2 sequences (50 % of the database size)

            AlgoBIDEPlus_withStrings algo = new AlgoBIDEPlus_withStrings();  //

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase, ".//output.txt", minsup);
            algo.printStatistics(sequenceDatabase.size());
        });
    }
}