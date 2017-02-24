package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Item;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Sequence;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceStatsGenerator;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.TDAG.TDAGPredictor;
import org.junit.Test;

/**
 * Example of how to use the TDAG sequence prediction model in the source code.
 * Copyright 2015.
 */
public class MainTestTDAG {

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

            // Train the prediction model
            TDAGPredictor predictionModel = new TDAGPredictor("TDAG");
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

        });
    }
}
