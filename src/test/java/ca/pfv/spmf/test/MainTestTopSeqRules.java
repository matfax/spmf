package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.topseqrules_and_tns.AlgoTopSeqRules;
import ca.pfv.spmf.datastructures.redblacktree.RedBlackTree;
import ca.pfv.spmf.input.sequence_database_array_integers.SequenceDatabase;
import org.junit.Test;

/**
 * * Example of how to use the TopSeqRules algorithm in source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2012)
 */
public class MainTestTopSeqRules {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // load database
            SequenceDatabase sequenceDatabase = new SequenceDatabase();
            try {
                sequenceDatabase.loadFile("contextPrefixSpan.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
            sequenceDatabase.printDatabaseStats();

            int k = 3;
            double minconf = 0.8;
//
            AlgoTopSeqRules algo = new AlgoTopSeqRules();
            RedBlackTree<ca.pfv.spmf.algorithms.sequential_rules.topseqrules_and_tns.Rule> rules = algo.runAlgorithm(k, sequenceDatabase, minconf);
            algo.printStats();
            algo.writeResultTofile(".//output.txt");   // to save results to file
        });
    }
}
