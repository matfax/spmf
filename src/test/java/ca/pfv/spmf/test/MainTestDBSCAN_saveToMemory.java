package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.dbscan.AlgoDBSCAN;
import ca.pfv.spmf.patterns.cluster.Cluster;
import ca.pfv.spmf.patterns.cluster.DoubleArray;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to use the DBSCAN algorithm, in source code.
 */
public class MainTestDBSCAN_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "inputDBScan2.txt";

            // we set the parameters of DBScan:
            int minPts = 2;
            double epsilon = 2d;

            // We specify that in the input file, double values on each line are separated by spaces
            String separator = " ";

            // Apply the algorithm
            AlgoDBSCAN algo = new AlgoDBSCAN();

            List<Cluster> clusters = algo.runAlgorithm(input, minPts, epsilon, separator);
            algo.printStatistics();

            // Print the clusters found by the algorithm
            // For each cluster:
            int i = 0;
            for (Cluster cluster : clusters) {
                System.out.println("Cluster " + i++);
                // For each data point:
                for (DoubleArray dataPoint : cluster.getVectors()) {
                    System.out.println("   " + dataPoint);
                }
            }
        });
    }


}
