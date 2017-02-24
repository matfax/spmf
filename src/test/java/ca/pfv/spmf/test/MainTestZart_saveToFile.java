package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.zart.AlgoZart;
import ca.pfv.spmf.algorithms.frequentpatterns.zart.TFTableFrequent;
import ca.pfv.spmf.algorithms.frequentpatterns.zart.TZTableClosed;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import org.junit.Test;

/**
 * Example of how to use the Zart Algorithm in source code.
 *
 * @author Philippe Fournier-Viger, 2008
 */
public class MainTestZart_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextZart.txt"; // the database
            String output = ".//output.txt";  // the path for saving the frequent itemsets found

            // Load a binary context
            TransactionDatabase context = new TransactionDatabase();
            context.loadFile(input);

            // Apply the Zart algorithm
            double minsup = 0.4;
            AlgoZart zart = new AlgoZart();
            TZTableClosed results = zart.runAlgorithm(context, minsup);
            TFTableFrequent frequents = zart.getTableFrequent();
            zart.printStatistics();
            zart.saveResultsToFile(output);

        });
    }
}
