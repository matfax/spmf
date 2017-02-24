package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.defme.AlgoDefMe;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.patterns.itemset_array_integers_with_tids_bitset.Itemset;
import ca.pfv.spmf.patterns.itemset_array_integers_with_tids_bitset.Itemsets;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * Example of how to use DefMe algorithm from the source code.
 *
 * @author Philippe Fournier-Viger - 2009
 */
public class MainTestDefMe_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the binary context
            String input = "contextZart.txt"; // the database

            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Applying the DefMe algorithm
            AlgoDefMe algo = new AlgoDefMe();
            Itemsets generators = algo.runAlgorithm(null, database, minsup);
            algo.printStats();
            for (List<Itemset> genSizeK : generators.getLevels()) {
                for (Itemset itemset : genSizeK) {
                    System.out.println(Arrays.toString(itemset.getItems()) + " #SUP: " + itemset.getAbsoluteSupport());
                }
            }
        });
    }
}
