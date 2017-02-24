package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.rulegrowth.AlgoERMiner;
import org.junit.Test;

/**
 * Example of how to use the ERMiner algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestERMiner {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPrefixSpan.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            //  Applying ERMiner algorithm with minsup = 3 sequences and minconf = 0.5
            int minsup_relative = 3;
            double minconf = 0.5;
            AlgoERMiner algo = new AlgoERMiner();
            algo.runAlgorithm(input, output, minsup_relative, minconf);

            // If you want to use an absolute support (percentage value), use
            // the following lines instead:

//		   double minsup_absolute = 0.75;  // it means 75 %
//		   AlgoERMiner algo = new AlgoERMiner();
//		   algo.runAlgorithm(minsup_absolute, minconf, input, output);

            // print statistics
            algo.printStats();

        });
    }
}