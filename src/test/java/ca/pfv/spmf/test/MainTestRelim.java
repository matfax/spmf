package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.relim.AlgoRelim;
import org.junit.Test;

/**
 * Example of how to use the RELIM Algorithm in source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestRelim {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPasquier99.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.40; // means a minsup of 2 transaction (we used a relative support)

            // Applying the RELIM algorithm
            AlgoRelim algo = new AlgoRelim();
            algo.runAlgorithm(minsup, input, output);
            algo.printStatistics();

        });
    }
}
