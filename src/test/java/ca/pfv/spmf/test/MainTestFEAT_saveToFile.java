package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoFEAT;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import org.junit.Test;


/**
 * Example of how to use the FEAT algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestFEAT_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String outputPath = "output.txt";
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpan.txt");
            // print the database to console
            sequenceDatabase.print();

            // Create an instance of the algorithm with minsup = 50 %
            AlgoFEAT algo = new AlgoFEAT();

            int minsup = 2; // we use a minimum support of 2 sequences.

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            algo.setShowSequenceIdentifiers(false);

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase, minsup);
            algo.writeResultTofile("output.txt");
            algo.printStatistics(sequenceDatabase.size());
        });
    }
}