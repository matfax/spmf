package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceEuclidian;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceFunction;
import ca.pfv.spmf.algorithms.clustering.kmeans.AlgoKMeans;
import ca.pfv.spmf.patterns.cluster.ClusterWithMean;
import ca.pfv.spmf.patterns.cluster.DoubleArray;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to use the KMEans algorithm, in source code.
 */
public class MainTestKMeans_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "inputDBScan2.txt";
            // we request 3 clusters
            int k = 3;

            // We specify that in the input file, double values on each line are separated by spaces
            String separator = " ";

            // Here we specify that we want to use the euclidian distance
            DistanceFunction distanceFunction = new DistanceEuclidian();
            // Alternative distance functions are also available such as:
//		DistanceFunction distanceFunction = new DistanceManathan(); 
//		DistanceFunction distanceFunction = new DistanceCosine(); 
//		DistanceFunction distanceFunction = new DistanceCorrelation(); 
//		DistanceFunction distanceFunction = new DistanceJaccard(); 

            // Apply the algorithm
            AlgoKMeans algoKMeans = new AlgoKMeans();
            List<ClusterWithMean> clusters = algoKMeans.runAlgorithm(input, k, distanceFunction, separator);
            algoKMeans.printStatistics();

            // Print the clusters found by the algorithm
            // For each cluster:
            int i = 0;
            for (ClusterWithMean cluster : clusters) {
                System.out.println("Cluster " + i++);
                // For each data point:
                for (DoubleArray dataPoint : cluster.getVectors()) {
                    System.out.println("   " + dataPoint);
                }
            }
        });
    }


}
