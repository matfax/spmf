package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.associationrules.fhsar.AlgoFHSAR;
import org.junit.Test;

/**
 * Example of how to use FHSAR algorithm from the source code.
 *
 * @author Philippe Fournier-Viger, 2011
 */
public class MainTestFHSAR {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextIGB.txt"; // the database
            String inputSAR = "sar.txt"; // the sensitive association rules that we want to hide
            String output = ".//output.txt";  // the path for saving the transformed database
            double minsup = 0.5;
            double minconf = 0.60;

            // STEP 1: Applying the FHSAR algorithm to hide association rules
            AlgoFHSAR algorithm = new AlgoFHSAR();
            algorithm.runAlgorithm(input, inputSAR, output, minsup, minconf);
            algorithm.printStats();

        });
    }
}
