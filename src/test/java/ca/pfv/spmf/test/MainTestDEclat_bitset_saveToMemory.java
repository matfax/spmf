package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.eclat.AlgoDEclat_Bitset;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Example of how to use DECLAT algorithm from the source code.
 *
 * @author Philippe Fournier-Viger - 2014
 */
public class MainTestDEclat_bitset_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the binary context
            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile("contextPasquier99.txt");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//		context.printContext();

            // Applying the DECLAT algorithm
            AlgoDEclat_Bitset algo = new AlgoDEclat_Bitset();
            Itemsets patterns = algo.runAlgorithm(null, database, 0.4, true);
            // NOTE 0: We use "null" as output file path, because in this
            // example, we want to save the result to memory instead of
            // saving to a file

            // NOTE 1: if you  use "true" in the line above, CHARM will use
            // a triangular matrix  for counting support of itemsets of size 2.
            // For some datasets it should make the algorithm faster.

            patterns.printItemsets(database.size());
            algo.printStats();

        });
    }
}
