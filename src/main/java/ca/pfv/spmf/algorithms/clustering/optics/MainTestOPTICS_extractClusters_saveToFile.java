package ca.pfv.spmf.algorithms.clustering.optics;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 *  Example of how to use the OPTICS algorithm, in source code.
 */
public class MainTestOPTICS_extractClusters_saveToFile {
	
	public static void main(String []args) throws NumberFormatException, IOException{
		
		
		/*
		 
		
		 
		String ca.pfv.spmf.input = fileToPath("housec8.txt");
		String output = ".//output.txt";
		
		// we set the parameters of DBScan:
		int minPts=3;
		double epsilon = 40d;
		
		// We specify that in the ca.pfv.spmf.input file, double values on each line are separated by spaces
		String separator = " ";
		
		// Apply the algorithm to compute a cluster ordering
		AlgoOPTICS algo = new AlgoOPTICS();  
		List<DoubleArrayOPTICS> clusterOrdering = algo.computerClusterOrdering(ca.pfv.spmf.input, minPts, epsilon, separator);
		
		// Print the cluster-ordering of points (for debugging)
		System.out.println("THE CLUSTER ORDERING:");
		System.out.println(" [data point] - reachability distance");
		for(DoubleArrayOPTICS arrayOP : clusterOrdering) {
			System.out.println(" " + Arrays.toString(arrayOP.data) + " -  " + arrayOP.reachabilityDistance);
		}
//		System.out.println(algo.kdtree.size());
//		System.out.println(clusterOrdering.size());
		
		//  automatically generate hierarchical clusters from the cluster ordering:
		double xi = 0.09;
		List<Cluster> clusters = algo.extractClusters(xi, minPts);
		
		// Print the clusters found by the algorithm
		// For each cluster:
		System.out.println("CLUSTER(S) FOUND:");
		int i=0;
		for(Cluster cluster : clusters) {
			System.out.println("Cluster " + i++);
			// For each data point:
			for(DoubleArray dataPoint : cluster.getVectors()) {
				System.out.println("   " + dataPoint);
			}
		}
		
		algo.printStatistics();
		algo.saveToFile(output);
		
		
		
		*/
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestOPTICS_extractClusters_saveToFile.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
	
	
}
