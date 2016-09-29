package ca.pfv.spmf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import ca.pfv.spmf.algorithms.frequentpatterns.pfpm.AlgoPFPM;

/**
 * Example of how to use the PFPM algorithm from the source code.
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestPFPM {

	public static void main(String [] arg) throws IOException{

		String output = ".//output.txt";
		String inputPath = "contextPFPM.txt";

		// =======================
		// EXAMPLE FROM THE ARTICLE : 
		String input = fileToPath(inputPath);
		int minPeriodicity = 1;  // transactions
		int maxPeriodicity = 3;  // transactions
		int minAveragePeriodicity = 1;  // transactions
		int maxAveragePeriodicity = 2;  // transactions
		// =======================

		// Applying the  algorithm
		AlgoPFPM algorithm = new AlgoPFPM();
		algorithm.setEnableESCP(true);
		algorithm.runAlgorithm(input, output, 
				minPeriodicity, maxPeriodicity, minAveragePeriodicity, 
				maxAveragePeriodicity);
		algorithm.printStats();

	}

	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestPFPM.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
