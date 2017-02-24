package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.rulegen.AlgoRuleGen;
import org.junit.Test;

/**
 * Example of how to use the RULEGEN Algorithm in source code.
 *
 * @author Philippe Fournier-Viger, 2012.
 */
public class MainTestRuleGen {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextPrefixSpan.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            int minsup = 3; // we use a minimum support of 3 sequences.
            double minconf = 0.75; // we use a minimum confidence of 50 %.

            // STEP 2: Generate the sequential rules with the RuleGen algorithm
            AlgoRuleGen rulegen = new AlgoRuleGen();
            rulegen.runAlgorithm(minsup, minconf, input, output);

            rulegen.printStats();
        });
    }
}