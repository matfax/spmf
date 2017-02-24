package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.cori.AlgoCORI;
import ca.pfv.spmf.algorithms.frequentpatterns.cori.ItemsetsCORI;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Example of how to use the CORI algorithm from the source code.
 *
 * @author Philippe Fournier-Viger - 2015
 */
public class MainTestCORI_saveToMemory {

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
            database.printDatabase();

            // minimum support
            double minsup = 0.8; // means 2 transaction (we used a relative support)
            // minimum bond
            double minbond = 0.2; // the minimum bond threhsold

            // Applying the algorithm
            AlgoCORI algo = new AlgoCORI();
            ItemsetsCORI patterns = algo.runAlgorithm(null, database, minsup, minbond, false);
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
