package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.defme.AlgoDefMe;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import org.junit.Test;

import java.io.IOException;


/**
 * Example of how to use DefMe algorithm from the source code.
 *
 * @author Philippe Fournier-Viger - 2009
 */
public class MainTestDefMe_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the binary context
            String input = "contextZart.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)

            TransactionDatabase database = new TransactionDatabase();
            try {
                database.loadFile(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Applying the DefMe algorithm
            AlgoDefMe algo = new AlgoDefMe();
            algo.runAlgorithm(output, database, minsup);
            algo.printStats();
        });
    }
}
