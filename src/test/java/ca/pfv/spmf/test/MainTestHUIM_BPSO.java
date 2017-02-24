package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.HUIM_BPSO.AlgoHUIM_BPSO;
import org.junit.Test;


/**
 * Example of how to use the HUIM-BPSO algorithm
 * from the source code.
 *
 * @author Jerry Chun-Wei Lin, Lu Yang, Philippe Fournier-Viger, 2016
 */
public class MainTestHUIM_BPSO {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextHUIM.txt";

            String output = ".//output.txt";

            int min_utility = 40;  //

            // Applying the huim_bpso algorithm
            AlgoHUIM_BPSO huim_bpso = new AlgoHUIM_BPSO();
            huim_bpso.runAlgorithm(input, output, min_utility);
            huim_bpso.printStats();

        });
    }
}
