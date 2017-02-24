package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.estDec.Algo_estDec;
import org.junit.Test;

/**
 * Class to test the estDec algorithm and save the result to a file.
 */
public class MainTest_estDec_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String database = "contextIGB.txt";
            String output = "output";

            double minsup = 0.1;
            double minsig = 0.4 * minsup;

            Algo_estDec algo = new Algo_estDec(minsup, minsig);

            // process a set of transactions from a file
            algo.processTransactionFromFile(database);

            // perform mining and save the result to a file
            algo.performMining_saveResultToFile(output);

            // print statistics
            algo.printStats();
        });
    }

}
