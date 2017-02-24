package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.spam.AlgoVMSP;
import ca.pfv.spmf.algorithms.sequentialpatterns.spam.PatternVMSP;
import org.junit.Test;

import java.util.List;
import java.util.TreeSet;


/**
 * Example of how to use the VMSP algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestVMSP_saveToMemory {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Load a sequence database
            String input = "dataVMSP.txt";
            String output = ".//output.txt";

            // Create an instance of the algorithm
            AlgoVMSP algo = new AlgoVMSP();
            algo.setMaximumPatternLength(8);
            algo.setMaxGap(1);

            // execute the algorithm with minsup = 2 sequences  (50 %)
            List<TreeSet<PatternVMSP>> maxPatterns = algo.runAlgorithm(input, output, 0.8);
            algo.printStatistics();

            // PRINT THE PATTTERNS FOUND
            for (TreeSet<PatternVMSP> tree : maxPatterns) {
                if (tree == null) {
                    continue;
                }
                // for each pattern
                for (PatternVMSP pattern : tree) {
                    System.out.println(" " + pattern.getPrefix() + "  support : " + pattern.getSupport());
                }
            }
        });
    }
}