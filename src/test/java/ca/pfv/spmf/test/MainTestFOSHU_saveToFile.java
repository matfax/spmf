package ca.pfv.spmf.test;


import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.foshu.AlgoFOSHU;
import org.junit.Test;

/**
 * Example of how to use the FOSHU algorithm  from the source code, and save the output to a file.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestFOSHU_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // This shows how to run the FOSHU algorithm on the database described in the paper.
            String input = "DB_FOSHU.txt";

            // Set a min-util ratio of 0.8
            double min_utility_ratio = 0.8d;

            // The result will be saved to a file "output.txt"
            String output = "output.txt";

            // Apply the algorithm
            AlgoFOSHU algo = new AlgoFOSHU();
            algo.runAlgorithm(input, output, min_utility_ratio);

            // The following line allows to specify how many lines from the input file should
            // be read. By default this parameter is set to Integer.MAX_VALUE (read the whole file).
//		algo.maxSEQUENCECOUNT =  9196;

            // Prin statistics about the algorithm execution
            algo.printStats();

        });
    }
}
