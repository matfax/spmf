package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_generator.SequenceDatabaseGenerator;
import org.junit.Test;

/**
 * Example of how to use the random sequence database generator, from
 * the source code, to generate a database with timestamps.
 */
public class MainTestGenerateSequenceDatabaseWithTimeStamps {
    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // BMS, Kosarak
            String outputFile = ".//output.txt";

            SequenceDatabaseGenerator generator = new SequenceDatabaseGenerator();
            generator.generateDatabase(5, 500, 2, 8, outputFile, true);
        });
    }
}
