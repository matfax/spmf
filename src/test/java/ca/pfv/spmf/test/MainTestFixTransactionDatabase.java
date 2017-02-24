package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.other_dataset_tools.FixTransactionDatabaseTool;
import org.junit.Test;

/**
 * Example of how to use the tool to fix common problem in a transaction database
 * in SPMF format.
 */
public class MainTestFixTransactionDatabase {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextIncorrect.txt";
            String outputFile = ".//output.txt";

            FixTransactionDatabaseTool tool = new FixTransactionDatabaseTool();
            tool.convert(inputFile, outputFile);
        });
    }
}
