package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.clusterreader.AlgoClusterReader;
import ca.pfv.spmf.patterns.cluster.Cluster;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to read clusters from a file
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestClusterReader {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input file
            String input = ("clustersDBScan.txt");

            // Applying the  algorithm
            AlgoClusterReader algorithm = new AlgoClusterReader();
            List<Cluster> clusters = algorithm.runAlgorithm(input);
            algorithm.printStats();

            // print the attribute names
            List<String> attributeNames = algorithm.getAttributeNames();
            System.out.println("ATTRIBUTES");
            for (String attributeName : attributeNames) {
                System.out.println(" " + attributeName);
            }

            // print the clusters
            System.out.println("Clusters");
            for (Cluster cluster : clusters) {
                System.out.println(" " + cluster);
            }
        });
    }
}
