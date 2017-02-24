package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceEuclidian;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceFunction;
import ca.pfv.spmf.algorithms.clustering.hierarchical_clustering.AlgoHierarchicalClustering;
import org.junit.Test;

/**
 * Example of how to use the hierarchical clustering algorithm
 * from the source code.
 */
public class MainTestHierarchicalClustering_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "inputDBScan2.txt";
            String output = ".//output.txt";
            int maxdistance = 4;

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
            AlgoHierarchicalClustering algo = new AlgoHierarchicalClustering();  // we request 3 clusters
            algo.runAlgorithm(input, maxdistance, distanceFunction, separator);
            algo.printStatistics();
            algo.saveToFile(output);

        });
    }


}
