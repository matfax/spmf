package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoCHUIMiner;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.Itemset;
import org.junit.Test;

import java.util.List;


/**
 * Example of how to use the CHUI-Miner algorithm
 * from the source code and save the result to memory.
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestCHUIMiner_saveToMemory {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // input file path
//		String input = "DB_Utility.txt";
//		// the minutility threshold
//		int min_utility = 30;   

            String input = "DB_Utility.txt";
            int min_utility = 30;

            // (1) Applying the  CHUI-Miner algorithm to find
            // closed high utility itemsets (CHUIs)
            AlgoCHUIMiner chuiMiner = new AlgoCHUIMiner(true);
            List<List<Itemset>> closedItemsets = chuiMiner.runAlgorithm(input, min_utility, null);
            chuiMiner.printStats();

            //  (2) PRINTING THE ITEMSETS FOUND TO THE CONSOLE
            for (List<Itemset> itemsetsSameSize : closedItemsets) {
                for (Itemset itemset : itemsetsSameSize) {
                    System.out.println(itemset);
                }
            }

        });
    }
}
