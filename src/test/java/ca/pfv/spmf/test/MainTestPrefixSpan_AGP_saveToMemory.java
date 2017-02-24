package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.AlgoPrefixSpan_AGP;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.items.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.items.creators.AbstractionCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.items.creators.AbstractionCreator_Qualitative;
import org.junit.Test;

/**
 * Example of how to use the algorithm GSP, saving the results in the main memory
 *
 * @author agomariz
 */
public class MainTestPrefixSpan_AGP_saveToMemory {

    /**
     */
    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            double support = (double) 180 / 360;

            boolean keepPatterns = true;
            boolean verbose = false;

            // if you set the following parameter to true, the sequence ids of the sequences where
            // each pattern appears will be shown in the result
            boolean outputSequenceIdentifiers = false;

            AbstractionCreator abstractionCreator = AbstractionCreator_Qualitative.getInstance();

            SequenceDatabase sequenceDatabase = new SequenceDatabase(abstractionCreator);

            //sequenceDatabase.loadFile("salidaFormateadaCodificadaSinIDs.txt", support);
            sequenceDatabase.loadFile("contextPrefixSpan.txt", support);

            AlgoPrefixSpan_AGP algorithm = new AlgoPrefixSpan_AGP(support, abstractionCreator);

            System.out.println(sequenceDatabase.toString());

            algorithm.runAlgorithm(sequenceDatabase, keepPatterns, verbose, null, outputSequenceIdentifiers);
            System.out.println(algorithm.getNumberOfFrequentPatterns() + " patterns found.");
            System.out.println(algorithm.printStatistics());
        });
    }
}
