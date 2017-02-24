package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_converter.Formats;
import ca.pfv.spmf.tools.dataset_converter.TransactionDatabaseConverter;
import org.junit.Test;

/**
 * Example of how to convert a transaction database from the CSV format
 * to the SPMF format.
 */
public class MainTestConvertTransactionDatabaseCSVtoSPMF {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextCSV.txt";
            String outputFile = ".//output.txt";
            Formats inputFileformat = Formats.CSV_INTEGER;
            int sequenceCount = Integer.MAX_VALUE;

            TransactionDatabaseConverter converter = new TransactionDatabaseConverter();
            converter.convert(inputFile, outputFile, inputFileformat, sequenceCount);
        });
    }
}
