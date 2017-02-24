package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_converter.Formats;
import ca.pfv.spmf.tools.dataset_converter.SequenceDatabaseConverter;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Example of how to convert a sequence database from a given format
 * to the SPMF format.
 */
public class MainTestConvertSequenceDatabase {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextCSV.txt";
            String outputFile = ".//output.txt";
            Formats inputFileformat = Formats.CSV_INTEGER;
            int sequenceCount = Integer.MAX_VALUE;

            // If you want to specify a different encoding for the text file, you can replace this line:
            Charset charset = Charset.defaultCharset();
            // by this line :
//		 Charset charset = Charset.forName("UTF-8");
            // Or other encodings  "UTF-16" etc.

            SequenceDatabaseConverter converter = new SequenceDatabaseConverter();
            converter.convert(inputFile, outputFile, inputFileformat, sequenceCount, charset);
        });
    }
}
