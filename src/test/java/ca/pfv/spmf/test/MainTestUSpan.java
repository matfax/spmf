package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.uspan.AlgoUSpan;
import org.junit.Test;

/**
 * This file is for testing the USpan algorithm
 *
 * @author Philippe Fournier-Viger, 2015
 * @see Sequence
 * @see SequenceDatabase
 */
public class MainTestUSpan {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // the input database
            String input = "DataBase_HUSRM.txt";
            // the path for saving the patterns found
            String output = ".//output.txt";

            // the minimum utility threshold
            int minutil = 35;

            AlgoUSpan algo = new AlgoUSpan();

            // set the maximum pattern length (optional)
            algo.setMaxPatternLength(4);

            // run the algorithm
            algo.runAlgorithm(input, output, minutil);


            // print statistics
            algo.printStatistics();
        });
    }
}
