package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.d2hup.AlgoD2HUP;
import org.junit.Test;

/**
 * Example of how to use the D2HUP algorithm
 * from the source code and output the result to a file.
 *
 * @author Philippe Fournier-Viger, 2015
 */
public class MainTestD2HUP_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // set the input and output file path
            String input = "DB_Utility.txt";
            String output = ".//output.txt";

            // set the minimum utility threshold
            int min_utility = 30;

            // Apply the algorithm
            AlgoD2HUP algo = new AlgoD2HUP();
            algo.runAlgorithm(input, output, min_utility);

            // Print statistics about the algorithm execution
            algo.printStats();
        });
    }
}
