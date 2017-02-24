package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.instancereader.AlgoInstanceFileReader;
import ca.pfv.spmf.gui.instanceviewer.InstanceViewer;
import ca.pfv.spmf.patterns.cluster.DoubleArray;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to view clusters from the source code of SPMF.
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestInstancesViewer {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input file
//		String input = "inputDBScan2.txt";
            String input = "configKMeans.txt";

            // Parameters of the algorithm
            String separator = " ";

            // Applying the  algorithm
            AlgoInstanceFileReader algorithm = new AlgoInstanceFileReader();
            List<DoubleArray> instances = algorithm.runAlgorithm(input, separator);
            List<String> attributeNames = algorithm.getAttributeNames();
//		algorithm.printStats();

//		System.out.println("INSTANCES");
//		for(DoubleArray instance : instances){
//			System.out.println(" "  + instance);
//		}
            InstanceViewer viewer = new InstanceViewer(instances, attributeNames);
            viewer.setVisible(true);
        });
    }
}
