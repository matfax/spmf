package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import org.junit.Test;

/**
 * Example of how to mine all association rules with FPGROWTH
 * and use the lift, and save the result to a file,
 * from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2013)
 */
public class MainTestAllAssociationRules_FPGrowth_saveToFile_withLift {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String input = "contextIGB.txt";
            String output = ".//output.txt";

            // STEP 1: Applying the FP-GROWTH algorithm to find frequent itemsets
            double minsupp = 0.5;
            AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
            Itemsets patterns = fpgrowth.runAlgorithm(input, null, minsupp);
//		patterns.printItemsets(database.size());
            int databaseSize = fpgrowth.getDatabaseSize();
            fpgrowth.printStats();

            // STEP 2: Generating all rules from the set of frequent itemsets (based on Agrawal & Srikant, 94)
            double minlift = 0.1;
            double minconf = 0.50;
            AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
            algoAgrawal.runAlgorithm(patterns, output, databaseSize, minconf, minlift);
            algoAgrawal.printStats();
        });
    }
}
