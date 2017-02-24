package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.clustering.text_clusterer.TextClusterAlgo;
import org.junit.Test;

/**
 * Example of how to use the Text Clusterer from the source code
 *
 * @author Sabarish Raghu (Copyright 2015)
 */
public class MainTestTextClusterer {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "input_text_clustering.txt";
            String output = "output.txt";
            boolean performStemming = true;
            boolean removeStopWords = true;

            TextClusterAlgo algo = new TextClusterAlgo();
            algo.runAlgorithm(input, output, performStemming, removeStopWords);
            algo.printStatistics();
        });
    }
}
