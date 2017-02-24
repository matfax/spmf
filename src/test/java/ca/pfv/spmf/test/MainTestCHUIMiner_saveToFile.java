package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoCHUIMiner;
import org.junit.Test;


/**
 * Example of how to use the CHUI-Miner algorithm
 * from the source code and save the result to file.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestCHUIMiner_saveToFile {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // input file path
            String input = "DB_Utility.txt";
            // the minutility threshold
            int min_utility = 25;

            // output file path
            String output = ".//CHUIs.txt";

            // (1) Applying the CHUI-Miner algorithm to find
            // closed high utility itemsets (CHUIs)
            AlgoCHUIMiner chuiMiner = new AlgoCHUIMiner(true);
            chuiMiner.runAlgorithm(input, min_utility, output);
            chuiMiner.printStats();
        });
    }
}
