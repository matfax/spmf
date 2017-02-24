package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_generator.TransactionDatasetUtilityGenerator;
import org.junit.Test;

/**
 * Example of how to generate utility values randomly for a transaction database
 * in SPMF format.
 */
public class MainTestTransactionDatabaseUtilityGenerator {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextPasquier99.txt";
            String outputFile = ".//output.txt";

            // The maximum quantity of an item in a transaction will be 10
            int maxQuantityOfItemInTransaction = 10;
            // the external utility of items will be generate by Random.nextGaussian()
            // and will be multiplied by this value
            double externalUtilityMultiplicativeFactor = 1d;

            // generat the utility values
            TransactionDatasetUtilityGenerator converter = new TransactionDatasetUtilityGenerator();
            converter.convert(inputFile, outputFile, maxQuantityOfItemInTransaction,
                    externalUtilityMultiplicativeFactor);
        });
    }
}
