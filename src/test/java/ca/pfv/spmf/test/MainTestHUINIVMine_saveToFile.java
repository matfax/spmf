package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.two_phase.AlgoHUINIVMine;
import ca.pfv.spmf.algorithms.frequentpatterns.two_phase.ItemsetsTP;
import ca.pfv.spmf.algorithms.frequentpatterns.two_phase.UtilityTransactionDatabaseTP;
import org.junit.Test;

/**
 * Example of how to use the HUINIVMine Algorithm in source code.
 *
 * @author Philippe Fournier-Viger, 2010
 */
public class MainTestHUINIVMine_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_NegativeUtility.txt";
            String output = ".//output.txt";

            int min_utility = 30;  //

            // Loading the database into memory
            UtilityTransactionDatabaseTP database = new UtilityTransactionDatabaseTP();
            database.loadFile(input);

            // Applying the Two-Phase algorithm
            AlgoHUINIVMine algo = new AlgoHUINIVMine();
            ItemsetsTP highUtilityItemsets = algo.runAlgorithm(database, min_utility);

            highUtilityItemsets.saveResultsToFile(output, database.getTransactions().size());

            algo.printStats();

        });
    }
}
