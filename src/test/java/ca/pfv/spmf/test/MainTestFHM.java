package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoFHM;
import org.junit.Test;

/**
 * Example of how to use the FHM algorithm
 * from the source code.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestFHM {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            int min_utility = 30;  //

            // Applying the HUIMiner algorithm
            AlgoFHM fhm = new AlgoFHM();
            fhm.runAlgorithm(input, output, min_utility);
            fhm.printStats();

        });
    }
}
