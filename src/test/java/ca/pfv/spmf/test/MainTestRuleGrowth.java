package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.rulegrowth.AlgoRULEGROWTH;
import org.junit.Test;

/**
 * Example of how to use the RuleGrowth Algorithm in source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2010)
 */
public class MainTestRuleGrowth {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String input = "contextPrefixSpan.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            //  Applying RuleGROWTH algorithm with minsup = 3 sequences and minconf = 0.5
            int minsup_relative = 3;
            double minconf = 0.5;
            AlgoRULEGROWTH algo = new AlgoRULEGROWTH();

//		// This optional parameter allows to specify the maximum number of items in the 
//		// left side (antecedent) of rules found:
//		algo.setMaxAntecedentSize(1);  // optional
//
//		// This optional parameter allows to specify the maximum number of items in the 
//		// right side (consequent) of rules found:
//		algo.setMaxConsequentSize(2);  // optional

            algo.runAlgorithm(input, output, minsup_relative, minconf);

            // If you want to use an absolute support (percentage value), use
            // the following lines instead:

//		   double minsup_absolute = 0.75;  // it means 75 %
//		   AlgoRULEGROWTH algo = new AlgoRULEGROWTH();
//		   algo.runAlgorithm(minsup_absolute, minconf, input, output);

            // print statistics
            algo.printStats();
        });
    }
}
