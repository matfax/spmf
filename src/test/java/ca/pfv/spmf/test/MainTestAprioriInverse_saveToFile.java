package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.apriori_inverse.AlgoAprioriInverse;
import org.junit.Test;


/**
 * Example of how to use the AprioriInverse algorithm and save the output
 * to a file, from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestAprioriInverse_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading a binary context
            String inputFilePath = "contextInverse.txt";
            String outputFilePath = ".//output.txt";  // the path for saving the frequent itemsets found

            // Note that we set the output file path to null because
            // we want to keep the result in memory instead of saving them
            // to an output file in this example.

            // the thresholds that we will use:
            double minsup = 0.001;
            double maxsup = 0.6;

            // Applying the APRIORI-Inverse algorithm to find sporadic itemsets
            AlgoAprioriInverse apriori2 = new AlgoAprioriInverse();
            // apply the algorithm
            apriori2.runAlgorithm(minsup, maxsup, inputFilePath, outputFilePath);
            apriori2.getDatabaseSize();
            apriori2.printStats();
        });
    }
}
