package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Item;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Sequence;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceStatsGenerator;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.CPT.CPT.CPTPredictor;
import org.junit.Test;

/**
 * Example of how to use the CPT+ sequence prediction model in the source code.
 * Copyright 2015.
 */
public class MainTestCPT {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // Load the set of training sequences
            String inputPath = "contextCPT.txt";
            SequenceDatabase trainingSet = new SequenceDatabase();
            trainingSet.loadFileSPMFFormat(inputPath, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);

            // Print the training sequences to the console
            System.out.println("--- Training sequences ---");
            for (Sequence sequence : trainingSet.getSequences()) {
                System.out.println(sequence.toString());
            }
            System.out.println();

            // Print statistics about the training sequences
            SequenceStatsGenerator.prinStats(trainingSet, " training sequences ");

            // The following line is to set optional parameters for the prediction model.
            // We want to
            // activate the CCF and CBS strategies which generally improves its performance (see paper)
            String optionalParameters = "splitLength:6 recursiveDividerMin:1 recursiveDividerMax:5";

            // Train the prediction model
            CPTPredictor predictionModel = new CPTPredictor("CPT", optionalParameters);
            predictionModel.Train(trainingSet.getSequences());

            // Now we will make a prediction.
            // We want to predict what would occur after the sequence <1, 3>.
            // We first create the sequence
            Sequence sequence = new Sequence(0);
            sequence.addItem(new Item(1));
            sequence.addItem(new Item(4));

            // Then we perform the prediction
            Sequence thePrediction = predictionModel.Predict(sequence);
            System.out.println("For the sequence <(1),(4)>, the prediction for the next symbol is: +" + thePrediction);


        });
    }
}
