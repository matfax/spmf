package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.aprioriTIDClose.AlgoAprioriTIDClose;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Example of how to use the AprioriTIDClose algorithm, from the
 * source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestAprioriTIDClose_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // the path for saving the frequent itemsets found
            String output = ".//output.txt";

            // Loading the binary context
            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile("contextPasquier99.txt");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//		database.printDatabase();

            // Applying the APRIORI-CLOSE algorithm
            AlgoAprioriTIDClose algo = new AlgoAprioriTIDClose();

//		// Set this variable to true to show the transaction identifiers where patterns appear in the output file
//		algo.setShowTransactionIdentifiers(true);

            // We pass null as output file path, because we want to keep
            // the result into memory instead.
            algo.runAlgorithm(database, 0.4, output);

            algo.printStats();

        });
    }
}
