package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.dci_closed_optimized.AlgoDCI_Closed_Optimized;
import org.junit.Test;

/**
 * Example of how to use DCI_Closed algorithm from the source code.
 * (optimized version)
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestDCI_Closed_Optimized {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPasquier99.txt";
            String output = ".//output.txt";
            int minsup = 2;  // means 2 transactions (we use a relative support)

            // Applying the  algorithm
            AlgoDCI_Closed_Optimized algorithm = new AlgoDCI_Closed_Optimized();

            // Set this variable to true to show the transaction identifiers where patterns appear in the output file
//		algorithm.setShowTransactionIdentifiers(true);

            algorithm.runAlgorithm(input, output, minsup);
        });
    }
}
