package ca.pfv.spmf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Item;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Sequence;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceStatsGenerator;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.CPT.CPTPlus.CPTPlusPredictor;

/**
 * Example of how to use the CPT+ sequence prediction model in the source code.
 * Copyright 2015.
 */
public class MainTestCPTPlus {

	public static void main(String [] arg) throws IOException{
		
		// Load the set of training sequences
		String inputPath = fileToPath("contextCPT.txt");  
		SequenceDatabase trainingSet = new SequenceDatabase();
		trainingSet.loadFileSPMFFormat(inputPath, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
		
		// Print the training sequences to the console
		System.out.println("--- Training sequences ---");
		for(Sequence sequence : trainingSet.getSequences()) {
			System.out.println(sequence.toString());
		}
		System.out.println();
		
		// Print statistics about the training sequences
		SequenceStatsGenerator.prinStats(trainingSet, " training sequences ");
		
		// The following line is to set optional parameters for the prediction model. 
		// We want to 
		// activate the CCF and CBS strategies which generally improves its performance (see paper)
		String optionalParameters = "CCF:true CBS:true CCFmin:1 CCFmax:6 CCFsup:2 splitMethod:0 minPredictionRatio:1.0 noiseRatio:1.0";
		// 			List<List<Item>> itemsets = finder.findFrequentItemsets(trainingSequences, parameters.paramInt("CCFmin"), parameters.paramInt("CCFmax"), parameters.paramInt("CCFsup"));
		
		// Train the prediction model
		CPTPlusPredictor predictionModel = new CPTPlusPredictor("CPT+", optionalParameters);
		predictionModel.Train(trainingSet.getSequences());
		
		// Now we will make a prediction.
		// We want to predict what would occur after the sequence <1, 3>.
		// We first create the sequence
		Sequence sequence = new Sequence(0);
		sequence.addItem(new Item(1));
		sequence.addItem(new Item(2));
		
		// Then we perform the prediction
		Sequence thePrediction = predictionModel.Predict(sequence);
		System.out.println("For the sequence <(1),(2)>, the prediction for the next symbol is: +" + thePrediction);
		
		

	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestCPTPlus.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
