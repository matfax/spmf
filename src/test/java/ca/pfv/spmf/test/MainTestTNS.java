package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.topseqrules_and_tns.AlgoTNS;
import ca.pfv.spmf.algorithms.sequential_rules.topseqrules_and_tns.Rule;
import ca.pfv.spmf.datastructures.redblacktree.RedBlackTree;
import ca.pfv.spmf.input.sequence_database_array_integers.SequenceDatabase;
import org.junit.Test;


/**
 * Example of how to use the TNS algorithm in source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestTNS {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load database into memory
            SequenceDatabase database = new SequenceDatabase();
            database.loadFile("contextPrefixSpan.txt");

            int k = 30;
            double minConf = 0.5;
            int delta = 2;

            AlgoTNS algo = new AlgoTNS();
            RedBlackTree<Rule> kRules = algo.runAlgorithm(k, database, minConf, delta);
            algo.writeResultTofile(".//output.txt");   // to save results to file

            algo.printStats();
        });
    }
}
