package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.optics.AlgoOPTICS;
import org.junit.Test;

/**
 * Example of how to use the OPTICS algorithm from the source code to obtain the OPTICS cluster
 * ordering of points and save the result to file.
 */
public class MainTestOPTICS_extractClusterOrdering_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "inputDBScan2.txt";
            String output = ".//output.txt";

            // we set the parameters of DBScan:
            int minPts = 2;
            double epsilon = 2d;

            // We specify that in the input file, double values on each line are separated by spaces
            String separator = " ";

            // Apply the algorithm to compute a cluster ordering
            AlgoOPTICS algo = new AlgoOPTICS();
            algo.computerClusterOrdering(input, minPts, epsilon, separator);

            algo.printStatistics();
            algo.saveClusterOrderingToFile(output);
        });
    }


}
