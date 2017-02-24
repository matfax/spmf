package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.AlgoPrefixSpan_AGP;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.items.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.items.creators.AbstractionCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.prefixSpan_AGP.items.creators.AbstractionCreator_Qualitative;
import org.junit.Test;

/**
 * Example of how to use the algorithm GSP, saving the results in a given
 * file
 *
 * @author agomariz
 */
public class MainTestPrefixSpan_AGP_saveToFile {

    /**
     */
    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String output = ".//output.txt";
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

            //Put the concrete path file where we want to keep the output
            algorithm.runAlgorithm(sequenceDatabase, keepPatterns, verbose, output, outputSequenceIdentifiers);
            System.out.println(algorithm.getNumberOfFrequentPatterns() + " patterns found.");
            System.out.println(algorithm.printStatistics());
        });
    }
}
