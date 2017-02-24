package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.pascal.AlgoPASCAL;
import org.junit.Test;

/**
 * Example of how to use the PASCAL algorithm (hash-tree version),
 * from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestPascal {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextZart.txt";
            String output = "output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)


            // Applying the PASCAL algorithm
            AlgoPASCAL algorithm = new AlgoPASCAL();
            algorithm.runAlgorithm(minsup, input, output);
            algorithm.printStats();
        });
    }
}
