package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.upgrowth_ihup.AlgoIHUP;
import org.junit.Test;

/**
 * Example of how to use the UPGrowth algorithm
 * from the source code.
 *
 * @author (c) Prashant Barhate, 2014
 */
public class MainTestIHUP {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            int min_utility = 30;  //

            // Applying the IHUP algorithm
            AlgoIHUP algo = new AlgoIHUP();
            algo.runAlgorithm(input, output, min_utility);
            algo.printStats();
        });
    }
}
