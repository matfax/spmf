package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import ca.pfv.spmf.algorithms.associationrules.closedrules.AlgoClosedRules;
import ca.pfv.spmf.algorithms.frequentpatterns.charm.AlgoCharm_Bitset;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.patterns.itemset_array_integers_with_tids_bitset.Itemsets;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Example of how to mine closed association rules from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestClosedAssociationRules_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // input and output file paths
            String input = "contextZart.txt";
            String output = null;
            // Note: we here set the output file path to null because we want
            // to save the result to memory instead of saving it to a file.

            // the threshold
            double minsupp = 0.60;
            double minconf = 0.60;

            // Loading the transaction database
            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile(input);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//		database.printDatabase();

            // STEP 1: Applying the Charm algorithm to find frequent closed itemsets
            AlgoCharm_Bitset algo = new AlgoCharm_Bitset();
            Itemsets patterns = algo.runAlgorithm(null, database, minsupp, true, 10000);
            algo.printStats();
            patterns.printItemsets(database.size());

            // STEP 2: Generate all rules from the set of frequent itemsets (based on Agrawal & Srikant, 94)
            AlgoClosedRules algoClosedRules = new AlgoClosedRules();
            AssocRules rules = algoClosedRules.runAlgorithm(patterns, null, database.size(), minconf);
            algoClosedRules.printStats();
            rules.printRules(database.size());

        });
    }
}
