package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.uapriori.AlgoUApriori;
import ca.pfv.spmf.algorithms.frequentpatterns.uapriori.UncertainTransactionDatabase;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Example of how to use the UApriori Algorithm in source code.
 *
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestUApriori_saveToFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // Loading the binary context
            UncertainTransactionDatabase context = new UncertainTransactionDatabase();
            try {
                context.loadFile("contextUncertain.txt");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            context.printDatabase();

            String output = ".//output.txt";

            // Applying the UApriori algorithm
            AlgoUApriori apriori = new AlgoUApriori(context);
            apriori.runAlgorithm(0.1, output);
            apriori.printStats();
        });
    }
}
