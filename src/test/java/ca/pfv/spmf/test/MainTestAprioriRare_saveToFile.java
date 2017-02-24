package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.apriori_rare.AlgoAprioriRare;
import org.junit.Test;


/**
 * Example of how to use APRIORI-RARE and save the output to a file,
 * from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestAprioriRare_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            //Input and output file paths
            String inputFilePath = "contextZart.txt";
            String outputFilePath = ".//output.txt";

            // the threshold that we will use:
            double minsup = 0.6;

            // Applying the APRIORI-Inverse algorithm to find sporadic itemsets
            AlgoAprioriRare apriori2 = new AlgoAprioriRare();
            // apply the algorithm
            apriori2.runAlgorithm(minsup, inputFilePath, outputFilePath);
            apriori2.printStats();
        });
    }
}
