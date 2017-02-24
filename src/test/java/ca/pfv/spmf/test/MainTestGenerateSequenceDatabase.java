package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_generator.SequenceDatabaseGenerator;
import org.junit.Test;

/**
 * Example of how to use the random sequence database generator to
 * generate a sequence database, from the source code.
 */
public class MainTestGenerateSequenceDatabase {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String outputFile = ".//output.txt";

            SequenceDatabaseGenerator generator = new SequenceDatabaseGenerator();
            generator.generateDatabase(5, 500, 2, 8, outputFile, false);
        });
    }
}
