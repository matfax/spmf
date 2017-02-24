package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_converter.Formats;
import ca.pfv.spmf.tools.dataset_converter.SequenceDatabaseConverter;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Example of how to convert a transaction database in SPMF format to
 * a sequence database in SPMF format.
 */
public class MainTestConvertTransDBtoSeqDB {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextPasquier99.txt";
            String outputFile = ".//output.txt";
            Formats inputFileformat = Formats.SPMF_TRANSACTION_DB;
            int transaction_count = Integer.MAX_VALUE;

            // If you want to specify a different encoding for the text file, you can replace this line:
            Charset charset = Charset.defaultCharset();
            // by this line :
//		 Charset charset = Charset.forName("UTF-8");
            // Or other encodings  "UTF-16" etc.

            SequenceDatabaseConverter converter = new SequenceDatabaseConverter();
            converter.convert(inputFile, outputFile, inputFileformat, transaction_count, charset);
        });
    }
}
