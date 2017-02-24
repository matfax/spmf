package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_converter.Formats;
import ca.pfv.spmf.tools.dataset_converter.TransactionDatabaseConverter;
import org.junit.Test;

/**
 * Example of how to convert a sequence database in SPMF format to
 * a transaction database in SPMF format.
 */
public class MainTestConvertSeqDBToTransDB {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextPrefixSpan.txt";
            String outputFile = "output.txt";
            Formats inputFileformat = Formats.SPMF_SEQUENCE_DB;
            int sequenceCount = Integer.MAX_VALUE;

            TransactionDatabaseConverter converter = new TransactionDatabaseConverter();
            converter.convert(inputFile, outputFile, inputFileformat, sequenceCount);
        });
    }
}
