package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoFSGP;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.SequentialPattern;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import org.junit.Test;

import java.util.List;


/**
 * Example of how to use the FSGP algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestFSGP_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextPrefixSpan.txt");
            // print the database to console
            sequenceDatabase.print();

            // Create an instance of the algorithm
            AlgoFSGP algo = new AlgoFSGP();
//		algo.setMaximumPatternLength(3);

            // execute the algorithm with minsup = 50 %
            boolean performPruning = true;// to activate pruning of search space
            List<SequentialPattern> patterns = algo.runAlgorithm(sequenceDatabase, 0.5, performPruning);
            algo.printStatistics(sequenceDatabase.size());
            System.out.println(" == PATTERNS ==");
            for (SequentialPattern pattern : patterns) {
                System.out.println(pattern + " support : " + pattern.getAbsoluteSupport());
            }
        });
    }
}