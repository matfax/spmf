package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.SFUPMinerUemax.AlgoSFUPMinerUemax;
import org.junit.Test;


/**
 * Example of how to use the SFUPMinerUemax algorithm
 * from the source code.
 *
 * @author Jerry Chun-Wei Lin, Lu Yang, Philippe Fournier-Viger, 2016
 */
public class MainTestSFUPMinerUemax {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String input = "contextHUIM.txt";
            String output = ".//output.txt";

            // Applying the SFUPMinerUemax algorithm
            AlgoSFUPMinerUemax sfupMinerUemax = new AlgoSFUPMinerUemax();
            sfupMinerUemax.runAlgorithm(input, output);
            sfupMinerUemax.printStats();

        });
    }
}
