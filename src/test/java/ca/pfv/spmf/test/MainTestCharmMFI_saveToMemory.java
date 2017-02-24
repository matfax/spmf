package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.charm.AlgoCharmMFI;
import ca.pfv.spmf.algorithms.frequentpatterns.charm.AlgoCharm_Bitset;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.patterns.itemset_array_integers_with_tids_bitset.Itemset;
import ca.pfv.spmf.patterns.itemset_array_integers_with_tids_bitset.Itemsets;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Example of how to use the CHARM-MFI algorith, from the source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2009)
 */
public class MainTestCharmMFI_saveToMemory {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the file paths
            String input = "contextPasquier99.txt"; // the database

            // minimum support
            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            // Loading the binary context
            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            database.printDatabase();

            // Applying the Charm algorithm
            AlgoCharm_Bitset algo = new AlgoCharm_Bitset();
            algo.runAlgorithm(null, database, minsup, false, 100000);
            // if you change use "true" in the line above, CHARM will use
            // a triangular matrix  for counting support of itemsets of size 2.
            // For some datasets it should make the algorithm faster.

            // Run CHARM MFI
            AlgoCharmMFI algo2 = new AlgoCharmMFI();
            algo2.runAlgorithm(null, algo.getClosedItemsets());

            // Code to browse the itemsets in memory
            System.out.println(" ===== MAXIMAL ITEMSETS FOUND ====");
            Itemsets itemsets = algo2.getItemsets();
            for (List<Itemset> level : itemsets.getLevels()) {
                for (Itemset itemset : level) {
                    for (Integer item : itemset.itemset) {
                        System.out.print(item);
                    }
                    System.out.println("  support " + itemset.getAbsoluteSupport());
                }
            }

            // Print statistics about the algorithm execution
            algo2.printStats(database.size());
        });
    }
}
