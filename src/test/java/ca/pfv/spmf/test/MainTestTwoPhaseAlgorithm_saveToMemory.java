package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.two_phase.AlgoTwoPhase;
import ca.pfv.spmf.algorithms.frequentpatterns.two_phase.ItemsetsTP;
import ca.pfv.spmf.algorithms.frequentpatterns.two_phase.UtilityTransactionDatabaseTP;
import org.junit.Test;

/**
 * Example of how to use the TWOPhase Algorithm in source code.
 *
 * @author Philippe Fournier-Viger, 2010
 */
public class MainTestTwoPhaseAlgorithm_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "DB_Utility.txt";

            int min_utility = 30;  //

            // Loading the database into memory
            UtilityTransactionDatabaseTP database = new UtilityTransactionDatabaseTP();
            database.loadFile(input);
            database.printDatabase();

            // Applying the Two-Phase algorithm
            AlgoTwoPhase twoPhase = new AlgoTwoPhase();
            ItemsetsTP highUtilityItemsets = twoPhase.runAlgorithm(database, min_utility);

            // print the frequent itemsets found
            highUtilityItemsets.printItemsets(database.size());

            twoPhase.printStats();

        });
    }
}
