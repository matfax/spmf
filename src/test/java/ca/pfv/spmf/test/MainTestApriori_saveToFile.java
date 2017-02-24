package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import org.junit.Test;

/**
 * Example of how to use APRIORI algorithm from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestApriori_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPasquier99.txt";
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            // Applying the Apriori algorithm
            AlgoApriori apriori = new AlgoApriori();
            apriori.runAlgorithm(minsup, input, output);
            apriori.printStats();
        });
    }
}
