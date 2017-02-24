package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.dci_closed.AlgoDCI_Closed;
import org.junit.Test;

/**
 * Example of how to use DCI_Closed algorithm from the source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestDCI_Closed {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPasquier99.txt";
            String output = ".//output.txt";
            int minsup = 2;  // means 2 transactions (we used a relative support)

            // Applying the  algorithm
            AlgoDCI_Closed algorithm = new AlgoDCI_Closed();
            algorithm.runAlgorithm(input, output, minsup);
        });
    }
}
