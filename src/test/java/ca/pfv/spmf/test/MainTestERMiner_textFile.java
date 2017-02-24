package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequential_rules.rulegrowth.AlgoERMiner;
import ca.pfv.spmf.tools.dataset_converter.SequenceDatabaseConverter;
import ca.pfv.spmf.tools.resultConverter.ResultConverter;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Example of how to use the ERMiner algorithm in source code with a text file.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestERMiner_textFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // If you want to specify a different encoding for the text file, you can replace this line:
            Charset charset = Charset.defaultCharset();
            // by this line :
//		 Charset charset = Charset.forName("UTF-8");
            // Or other encodings  "UTF-16" etc.


            SequenceDatabaseConverter converter = new SequenceDatabaseConverter();
            Map<Integer, String> mapping = converter.convertTEXTandReturnMap("example2.text",
                    "example2_converted.txt", Integer.MAX_VALUE, charset);

            //  Applying ERMiner algorithm with minsup = 3 sequences and minconf = 0.5

            AlgoERMiner algo = new AlgoERMiner();
            algo.runAlgorithm("example2_converted.txt", "output.txt", 3, 0.5);

            ResultConverter converter2 = new ResultConverter();
            converter2.convert(mapping, "output.txt", "final_output.txt", charset);

            // print statistics
            algo.printStats();

        });
    }
}