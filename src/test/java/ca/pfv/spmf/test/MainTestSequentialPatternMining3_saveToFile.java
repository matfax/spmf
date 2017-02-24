package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.AlgoFournierViger08;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.kmeans_for_fournier08.AlgoKMeansWithSupport;
import ca.pfv.spmf.algorithms.sequentialpatterns.fournier2008_seqdim.kmeans_for_fournier08.AlgoKMeans_forFournier08;
import org.junit.Test;

/**
 * Example of  sequential pattern mining with integer
 * values.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestSequentialPatternMining3_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            //In this example, the result is saved to a file
            String outputFilePath = ".//output.txt";

            // Load a sequence database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            sequenceDatabase.loadFile("contextSequencesTimeExtended_ValuedItems.txt");
//		sequenceDatabase.print();

            // we create the clustering algorithm to be used.
            // we create the clustering algorithm to be used.
            AlgoKMeansWithSupport algoKMeansWithSupport =
                    new AlgoKMeansWithSupport(5, 0.50, sequenceDatabase.size(), new AlgoKMeans_forFournier08(5), 1);

            // Create an instance of the algorithm
            AlgoFournierViger08 algo
                    = new AlgoFournierViger08(0.50,
                    0, Double.MAX_VALUE, 0, Double.MAX_VALUE, algoKMeansWithSupport, false, false);

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase, outputFilePath);
            algo.printStatistics();
        });
    }
}


