package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.optics.AlgoOPTICS;
import ca.pfv.spmf.algorithms.clustering.optics.DoubleArrayOPTICS;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to use the OPTICS algorithm from the source code to obtain the OPTICS cluster
 * ordering of points and keep the result in memory.
 */
public class MainTestOPTICS_extractClusterOrdering_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "inputDBScan2.txt";

            // we set the parameters of DBScan:
            int minPts = 2;
            double epsilon = 2d;

            // We specify that in the input file, double values on each line are separated by spaces
            String separator = " ";

            // Apply the algorithm to compute a cluster ordering
            AlgoOPTICS algo = new AlgoOPTICS();
            List<DoubleArrayOPTICS> clusterOrdering = algo.computerClusterOrdering(input, minPts, epsilon, separator);

            // Print the cluster-ordering of points to the console (for debugging)
            System.out.println("THE CLUSTER ORDERING:");
            System.out.println(" [data point] - reachability distance");
            for (DoubleArrayOPTICS arrayOP : clusterOrdering) {
                System.out.println(arrayOP.toString() + " " + arrayOP.reachabilityDistance);
            }
            algo.printStatistics();
        });
    }


}
