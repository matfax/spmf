package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.apriori_inverse.AlgoAprioriInverse;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import org.junit.Test;

/**
 * Example of how to use the APRIORI-INVERSE algorithm from the source
 * code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestAprioriInverse_saveToMemory {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading a binary context
            String inputFilePath = "contextInverse.txt";
            String outputFilePath = null;
            // Note that we set the output file path to null because
            // we want to keep the result in memory instead of saving them
            // to an output file in this example.

            // the thresholds that we will use:
            double minsup = 0.001;
            double maxsup = 0.6;

            // Applying the APRIORI-Inverse algorithm to find sporadic itemsets
            AlgoAprioriInverse apriori2 = new AlgoAprioriInverse();
            // apply the algorithm
            Itemsets patterns = apriori2.runAlgorithm(minsup, maxsup, inputFilePath, outputFilePath);
            int databaseSize = apriori2.getDatabaseSize();
            patterns.printItemsets(databaseSize); // print the result
        });
    }
}
