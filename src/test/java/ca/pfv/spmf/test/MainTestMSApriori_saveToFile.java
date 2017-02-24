package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.MSApriori.AlgoMSApriori;
import org.junit.Test;

/**
 * Example of how to use the MSAPRIORI algorithm in source code.
 */
public class MainTestMSApriori_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextIGB.txt";
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double beta = 0.4;
            double LS = 0.2;

            // Applying the MSApriori algorithm
            AlgoMSApriori apriori = new AlgoMSApriori();
            apriori.runAlgorithm(input, output, beta, LS);
            apriori.printStats();
        });
    }
}
