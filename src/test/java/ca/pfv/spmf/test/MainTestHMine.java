package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hmine.AlgoHMine;
import org.junit.Test;

/**
 * Example of how to use HMine from the source code.
 *
 * @author Philippe Fournier-Viger, 2011.
 */
public class MainTestHMine {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPasquier99.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.4; // 40% means a minsup of 2 transaction (we used a relative support)

            // Applying the  algorithm
            AlgoHMine algorithm = new AlgoHMine();
            algorithm.runAlgorithm(input, output, minsup);
            algorithm.printStats();
        });
    }
}
