package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.aprioriTID.AlgoAprioriTID;
import ca.pfv.spmf.patterns.itemset_array_integers_with_tids.Itemsets;
import org.junit.Test;

/**
 * Example of how to use the AprioriTID algorithm from the source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestAprioriTID {

    @Test
    public void main() {

        NoExceptionAssertion.assertDoesNotThrow(() -> {
            // Loading the binary context
            String inputfile = "contextPasquier99.txt";

            // Applying the AprioriTID algorithm
            AlgoAprioriTID apriori = new AlgoAprioriTID();

            // We run the algorithm.
            // Note: we pass a null value for the output file
            //      because we want to keep the result into memory
            //      instead of writing it to an output file.
            Itemsets patterns = apriori.runAlgorithm(inputfile, null, 0.4);
            patterns.printItemsets(apriori.getDatabaseSize());
            apriori.printStats();
        });
    }
}
