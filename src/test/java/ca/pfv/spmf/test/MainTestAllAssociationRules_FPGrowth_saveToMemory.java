package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import org.junit.Test;

/**
 * Example of how to mine all association rules with FPGROWTH,
 * from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestAllAssociationRules_FPGrowth_saveToMemory {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the binary context
            String input = "contextIGB.txt";

            // STEP 1: Applying the FP-GROWTH algorithm to find frequent itemsets
            double minsupp = 0.5;
            AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
            Itemsets patterns = fpgrowth.runAlgorithm(input, null, minsupp);
            int databaseSize = fpgrowth.getDatabaseSize();
            patterns.printItemsets(databaseSize);

            // STEP 2: Generating all rules from the set of frequent itemsets (based on Agrawal & Srikant, 94)
            double minconf = 0.60;
            AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
            // the next line run the algorithm.
            // Note: we pass null as output file path, because we don't want
            // to save the result to a file, but keep it into memory.
            AssocRules rules = algoAgrawal.runAlgorithm(patterns, null, databaseSize, minconf);
            rules.printRules(databaseSize);
            System.out.println("DATABASE SIZE " + databaseSize);

        });
    }
}
