package ca.pfv.spmf.test;


import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.fin_prepost.FIN;
import org.junit.Test;


/**
 * Example of how to use FIN algorithm from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2014)
 */
public class MainTestFIN {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPasquier99.txt";
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.3; // means a minsup of 2 transaction (we used a relative support)

            // Applying the algorithm
            FIN algorithm = new FIN();
            algorithm.runAlgorithm(input, minsup, output);
            algorithm.printStats();
        });
    }
}
