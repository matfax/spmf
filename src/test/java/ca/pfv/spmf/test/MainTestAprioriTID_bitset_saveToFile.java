package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.aprioriTID.AlgoAprioriTID_Bitset;
import org.junit.Test;

/**
 * Example of how to use the AprioriTID algorithm (bitset version), and
 * save the result to a file, from the source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestAprioriTID_bitset_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the binary context
            String input = "contextPasquier99.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            // Create an instance of the AprioriTID algorithm
            AlgoAprioriTID_Bitset algo = new AlgoAprioriTID_Bitset();

            // Set this variable to true to show the transaction identifiers where each pattern appear in the output file
//		algo.setShowTransactionIdentifiers(true);

            /// Run the algorithm
            algo.runAlgorithm(input, output, minsup);

            // Print statistics
            algo.printStats();
        });
    }
}
