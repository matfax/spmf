package ca.pfv.spmf.test;


import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.tshoun.AlgoTSHoun;
import ca.pfv.spmf.algorithms.frequentpatterns.tshoun.DatabaseWithPeriods;
import org.junit.Test;

/**
 * Example of how to use the TS-Houn in source code, and save the output to a file.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestTSHOUN_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {


            String input = "DB_FOSHU.txt";
            double min_utility_ratio = 0.8d;
            int periodCount = 3;

            String output = "./output.txt";

            // Loading the database into memory
            DatabaseWithPeriods database = new DatabaseWithPeriods(periodCount);
            database.loadFile(input);

            // Applying the algorithm
            AlgoTSHoun algo = new AlgoTSHoun();
            algo.runAlgorithm(database, min_utility_ratio, output, periodCount);
            algo.printStats();

        });
    }
}
