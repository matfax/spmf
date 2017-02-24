package ca.pfv.spmf.test;


import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.fin_prepost.PrePost;
import org.junit.Test;

/**
 * Example of how to use PrePost algorithm from the source code.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestPrePost {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPasquier99.txt";
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0; // means a minsup of 2 transaction (we used a relative support)

            // Applying the algorithm
            PrePost prepost = new PrePost();
            prepost.runAlgorithm(input, minsup, output);
            prepost.printStats();
        });
    }
}
