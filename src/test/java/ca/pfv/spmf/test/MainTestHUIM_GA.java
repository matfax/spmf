package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.HUIM_GA.AlgoHUIM_GA;
import org.junit.Test;


/**
 * Example of how to use the HUIM-GA algorithm
 * from the source code.
 *
 * @author Jerry Chun-Wei Lin, Lu Yang, Philippe Fournier-Viger, 2016
 */
public class MainTestHUIM_GA {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextHUIM.txt";

            String output = ".//output.txt";

            int min_utility = 40;  //

            // Applying the huim_bpso algorithm
            AlgoHUIM_GA huim_ga = new AlgoHUIM_GA();
            huim_ga.runAlgorithm(input, output, min_utility);
            huim_ga.printStats();

        });
    }
}
