package ca.pfv.spmf.algorithms.clustering.optics;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 *  Example of how to use the OPTICS algorithm from the source code to obtain the OPTICS cluster
 *  ordering of points and keep the result in memory.
 */
public class MainTestOPTICS_extractClusterOrdering_saveToMemory {
	
	public static void main(String []args) throws NumberFormatException, IOException{
		
		String input = fileToPath("inputDBScan.txt");
		
		// we set the parameters of DBScan:
		int minPts=2;
		double epsilon = 5d;
		
		// We specify that in the ca.pfv.spmf.input file, double values on each line are separated by spaces
		String separator = " ";
		
		// Apply the algorithm to compute a cluster ordering
		AlgoOPTICS algo = new AlgoOPTICS();  
		List<DoubleArrayOPTICS> clusterOrdering = algo.computerClusterOrdering(input, minPts, epsilon, separator);
		
		// Print the cluster-ordering of points to the console (for debugging)
		System.out.println("THE CLUSTER ORDERING:");
		System.out.println(" [data point] - reachability distance");
		for(DoubleArrayOPTICS arrayOP : clusterOrdering) {
			System.out.println(" " + Arrays.toString(arrayOP.data) + " -  " + arrayOP.reachabilityDistance);
		}
		algo.printStatistics();
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestOPTICS_extractClusterOrdering_saveToMemory.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
	
	
}
