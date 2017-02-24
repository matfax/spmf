package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.vme.AlgoVME;
import org.junit.Test;

/**
 * Example of how to use the VME Algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestVME {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // Loading the binary context
            String input = "contextVME.txt";  // the database
            String output = ".//output.txt";  // the path for saving the earasable itemsets found

            double threshold = 0.15; // a threshold of 15 %

            // Applying the  algorithm
            AlgoVME algo = new AlgoVME();
            algo.runAlgorithm(input, output, threshold);
            algo.printStats();

        });
    }
}
