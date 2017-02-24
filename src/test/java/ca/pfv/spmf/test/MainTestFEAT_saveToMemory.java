package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoFEAT;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.SequentialPattern;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import org.junit.Test;

import java.util.List;


/**
 * Example of how to use the FEAT algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestFEAT_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpan.txt");
            // print the database to console
            sequenceDatabase.print();

            // Create an instance of the algorithm
            AlgoFEAT algo = new AlgoFEAT();
//		algo.setMaximumPatternLength(3);

            // execute the algorithm with minsup = 50 %
            List<SequentialPattern> patterns = algo.runAlgorithm(sequenceDatabase, 0.4);
            algo.printStatistics(sequenceDatabase.size());
            System.out.println(" == PATTERNS ==");
            for (SequentialPattern pattern : patterns) {
                System.out.print(pattern + " support : " + pattern.getAbsoluteSupport() + " sequence ids:");
                for (Integer sequenceID : pattern.getSequenceIDs()) {
                    System.out.print(" " + sequenceID);
                }
                System.out.println();

            }
        });
    }
}