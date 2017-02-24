package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.clusterreader.AlgoClusterReader;
import ca.pfv.spmf.gui.clusterviewer.ClusterViewer;
import ca.pfv.spmf.patterns.cluster.Cluster;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to view clusters from the source code of SPMF.
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestClusterViewerFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input file
            String input = "clustersDBScan.txt";

            // Applying the  algorithm
            AlgoClusterReader algorithm = new AlgoClusterReader();
            List<Cluster> clusters = algorithm.runAlgorithm(input);
            List<String> attributeNames = algorithm.getAttributeNames();
            algorithm.printStats();

            ClusterViewer viewer = new ClusterViewer(clusters, attributeNames);
            viewer.setVisible(true);
        });
    }
}
