package ca.pfv.spmf.test;

import ca.pfv.spmf.algorithms.frequentpatterns.estDec.Algo_estDec;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * Class to test the estDec algorithm and save the result to a file.
 */
public class MainTest_estDec_saveToFile {

	@Test
	public void main() throws FileNotFoundException, IOException{
		
		String database = "contextIGB.txt";
		String  output = "output";
        
        double minsup = 0.1;
        double minsig = 0.4*minsup;

		Algo_estDec algo = new Algo_estDec(minsup,minsig);
		
		// process a set of transactions from a file
		algo.processTransactionFromFile(database);
		
		// perform mining and save the result to a file
		algo.performMining_saveResultToFile(output);
		
		// print statistics
		algo.printStats();
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTest_estDec_saveToFile.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
