package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.cori.AlgoCORI;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import org.junit.Test;

import java.io.IOException;


/**
 * Example of how to use the CORI algorithm from the source code.
 *
 * @author Philippe Fournier-Viger - 2015
 */
public class MainTestCORI_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the file paths
            String input = "contextPasquier99.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            // minimum support
            double minsup = 0.8; // means 2 transaction (we used a relative support)
            // minimum bond
            double minbond = 0.2; // the minimum bond threhsold

            // Loading the transaction database
            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
//		context.printContext();

            // Applying the ECLAT algorithm
            AlgoCORI algo = new AlgoCORI();

//		// Set this variable to true to show the transaction identifiers where patterns appear in the output file
//		algo.setShowTransactionIdentifiers(true);	

            algo.runAlgorithm(output, database, minsup, minbond, false);
            // if you change use "true" in the line above, ECLAT will use
            // a triangular matrix  for counting support of itemsets of size 2.
            // For some datasets it should make the algorithm faster.

            algo.printStats();

        });
    }
}
