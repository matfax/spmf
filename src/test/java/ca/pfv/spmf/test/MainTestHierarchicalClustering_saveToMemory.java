package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceEuclidian;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceFunction;
import ca.pfv.spmf.algorithms.clustering.hierarchical_clustering.AlgoHierarchicalClustering;
import ca.pfv.spmf.patterns.cluster.ClusterWithMean;
import ca.pfv.spmf.patterns.cluster.DoubleArray;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to use the hierarchical clustering algorithm
 * from the source code.
 */
public class MainTestHierarchicalClustering_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "inputDBScan2.txt";
            int maxdistance = 4;

            String separator = " ";

            // Here we specify that we want to use the euclidian distance
            DistanceFunction distanceFunction = new DistanceEuclidian();
            // Alternative distance functions are also available such as:
//		DistanceFunction distanceFunction = new DistanceManathan(); 
//		DistanceFunction distanceFunction = new DistanceCosine(); 
//		DistanceFunction distanceFunction = new DistanceCorrelation(); 
//		DistanceFunction distanceFunction = new DistanceJaccard(); 

            // Apply the algorithm
            AlgoHierarchicalClustering algo = new AlgoHierarchicalClustering();  // we request 3 clusters
            List<ClusterWithMean> clusters = algo.runAlgorithm(input, maxdistance, distanceFunction, separator);
            algo.printStatistics();

            // Print the clusters found by the algorithm
            // For each cluster:
            int i = 0;
            for (ClusterWithMean cluster : clusters) {
                System.out.println("Cluster " + i++ + " (containing " + cluster.getVectors().size() + " vector(s))");
                // For each data point:
                for (DoubleArray dataPoint : cluster.getVectors()) {
                    System.out.println("   " + dataPoint);
                }
            }

        });
    }


}
