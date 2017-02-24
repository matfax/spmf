package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.dbscan.AlgoDBSCAN;
import org.junit.Test;

/**
 * Example of how to use the DBSCAN algorithm, in source code.
 */
public class MainTestDBSCAN_saveToFile {

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

            // Apply the algorithm
            AlgoDBSCAN algo = new AlgoDBSCAN();

            algo.runAlgorithm(input, minPts, epsilon, separator);
            algo.printStatistics();
            algo.saveToFile(output);
        });
    }


}
