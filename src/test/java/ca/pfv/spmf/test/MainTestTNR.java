package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.AlgoTNR;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.Database;
import ca.pfv.spmf.algorithms.associationrules.TopKRules_and_TNR.RuleG;
import ca.pfv.spmf.datastructures.redblacktree.RedBlackTree;
import org.junit.Test;

/**
 * Example of how to use the TNR algorithm in source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2010)
 */
public class MainTestTNR {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load database into memory
            Database database = new Database();
            database.loadFile("contextIGB.txt");

            int k = 10;
            double minConf = 0.5;
            int delta = 2;

            AlgoTNR algo = new AlgoTNR();
            RedBlackTree<RuleG> kRules = algo.runAlgorithm(k, minConf, database, delta);
            algo.writeResultTofile("output.txt");   // to save results to file

            algo.printStats();
        });
    }
}
