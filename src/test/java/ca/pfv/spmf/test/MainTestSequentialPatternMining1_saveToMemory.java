package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.AlgoFournierViger08;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.SequenceDatabase;
import org.junit.Test;

/**
 * Example of sequential pattern mining with time constraints.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestSequentialPatternMining1_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextSequencesTimeExtended.txt");
            // Create an instance of the algorithm
            AlgoFournierViger08 algo
                    = new AlgoFournierViger08(0.55,
                    0, 2, 0, 2, null, false, false);

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase);
            algo.printResult(sequenceDatabase.size());
        });
    }
}




