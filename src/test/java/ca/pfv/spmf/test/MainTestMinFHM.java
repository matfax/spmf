package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoMinFHM;
import org.junit.Test;

/**
 * Example of how to use the MinFHM algorithm from the source code.
 *
 * @author Philippe Fournier-Viger, 2016
 */
public class MainTestMinFHM {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            int min_utility = 30;  //

            // Applying the  algorithm
            AlgoMinFHM algorithm = new AlgoMinFHM();
            algorithm.runAlgorithm(input, output, min_utility);
            algorithm.printStats();

        });
    }
}
