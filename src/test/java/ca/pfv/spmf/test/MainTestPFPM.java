package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.pfpm.AlgoPFPM;
import org.junit.Test;

/**
 * Example of how to use the PFPM algorithm from the source code.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestPFPM {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String output = ".//output.txt";
            String inputPath = "contextPFPM.txt";

            // =======================
            // EXAMPLE FROM THE ARTICLE :
            String input = inputPath;
            int minPeriodicity = 1;  // transactions
            int maxPeriodicity = 3;  // transactions
            int minAveragePeriodicity = 1;  // transactions
            int maxAveragePeriodicity = 2;  // transactions
            // =======================

            // Applying the  algorithm
            AlgoPFPM algorithm = new AlgoPFPM();
            algorithm.setEnableESCP(true);
            algorithm.runAlgorithm(input, output,
                    minPeriodicity, maxPeriodicity, minAveragePeriodicity,
                    maxAveragePeriodicity);
            algorithm.printStats();

        });
    }
}
