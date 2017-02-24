package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.HUIM_BPSO_tree.AlgoHUIM_BPSO_tree;
import org.junit.Test;

/**
 * Example of how to use the HUIM-BPSO-tree algorithm
 * from the source code.
 *
 * @author Jerry Chun-Wei Lin, Lu Yang, Philippe Fournier-Viger, 2016
 */
public class MainTestHUIM_BPSO_tree {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextHUIM.txt";

            String output = ".//output.txt";

            int min_utility = 40;  //

            // Applying the huim_bpso_tree algorithm
            AlgoHUIM_BPSO_tree huim_bpso_tree = new AlgoHUIM_BPSO_tree();
            huim_bpso_tree.runAlgorithm(input, output, min_utility);
            huim_bpso_tree.printStats();

        });
    }
}
