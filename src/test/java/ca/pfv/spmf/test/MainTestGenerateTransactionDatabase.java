package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_generator.TransactionDatabaseGenerator;
import org.junit.Test;

/**
 * Example of how to use the random transaction database generator
 * from the source code.
 */
public class MainTestGenerateTransactionDatabase {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String outputFile = ".//output.txt";
            TransactionDatabaseGenerator generator = new TransactionDatabaseGenerator();
            generator.generateDatabase(5, 500, 4, outputFile);
        });
    }
}
