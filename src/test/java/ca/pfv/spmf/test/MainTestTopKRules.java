package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.AlgoTopKRules;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.Database;
import org.junit.Test;

/**
 * Example of how to use the TOPKRULES algorithm in source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2010)
 */
public class MainTestTopKRules {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load database into memory
            Database database = new Database();
            database.loadFile("contextIGB.txt");

            int k = 10;
            double minConf = 0.8; //

            AlgoTopKRules algo = new AlgoTopKRules();
            algo.runAlgorithm(k, minConf, database);

            algo.printStats();
            algo.writeResultTofile(".//output.txt");   // to save results to file

        });
    }
}
