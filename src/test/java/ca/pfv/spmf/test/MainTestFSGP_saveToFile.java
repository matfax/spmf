package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoFSGP;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import org.junit.Test;


/**
 * Example of how to use the FSGP algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestFSGP_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String outputPath = ".//output.txt";
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpan.txt");
            // print the database to console
//		sequenceDatabase.print();

            // Create an instance of the algorithm with minsup = 50 %
            AlgoFSGP algo = new AlgoFSGP();

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            algo.setShowSequenceIdentifiers(false);

            int minsup = 2; // we use a minimum support of 2 sequences.

            // execute the algorithm
            boolean performPruning = true;// to activate pruning of search space
            algo.runAlgorithm(sequenceDatabase, minsup, performPruning);
            algo.writeResultTofile(outputPath);
            algo.printStatistics(sequenceDatabase.size());
        });
    }
}