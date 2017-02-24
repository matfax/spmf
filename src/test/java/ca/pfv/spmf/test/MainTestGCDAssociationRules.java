package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.gcd.GCDAssociationRules;
import org.junit.Test;

import java.util.Calendar;

/**
 * Example of how to use the GCD algorithm
 * from the source code and save the result to file.
 */
public class MainTestGCDAssociationRules {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // input file path
            String input = "contextPasquier99.txt";

            // output file path
            String output = "output.txt";

            double inputFreqThreshold = 0.47;
            double inputConfThreshold = 0.47;
            int combinationsElementsLimit = 3;
            GCDAssociationRules gcdRunner = new GCDAssociationRules(input, output, inputFreqThreshold,
                    inputConfThreshold, combinationsElementsLimit);

            long start = Calendar.getInstance().getTimeInMillis();
            gcdRunner.runAlgorithm();
            long end = Calendar.getInstance().getTimeInMillis();
            long ms = end - start;
            System.out.println("Number of rules found: " + gcdRunner.getPatternCount());
            System.out.println("Total in milliseconds: " + ms + " ms");
        });
    }
}
