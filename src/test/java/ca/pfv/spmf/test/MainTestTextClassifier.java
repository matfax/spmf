package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.classifiers.naive_bayes_text_classifier.AlgoNaiveBayesClassifier;
import org.junit.Test;

/**
 * Example of how to use the Text Classifier from the source code
 *
 * @author Sabarish Raghu (Copyright 2015)
 */
public class MainTestTextClassifier {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String trainingSetDirectory = "text_classification_set/Train/";
            System.out.println(trainingSetDirectory);
            String testSetDirectory = "text_classification_set/Test/";
            String outputDirectory = "text_classification_set";
            boolean memoryFlag = true; // if set to true, the data will be loaded in memory rather than repeatedly using file IO

            AlgoNaiveBayesClassifier nb = new AlgoNaiveBayesClassifier();
            nb.runAlgorithm(trainingSetDirectory, testSetDirectory, outputDirectory, memoryFlag);
        });
    }
}
