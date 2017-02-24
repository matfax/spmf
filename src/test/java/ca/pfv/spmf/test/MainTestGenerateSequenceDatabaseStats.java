package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_stats.SequenceStatsGenerator;
import org.junit.Test;

/**
 * Example of how to generate statistics about a sequence database
 */
public class MainTestGenerateSequenceDatabaseStats {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextPrefixSpan.txt";
            try {
                SequenceStatsGenerator sequenceDatabase = new SequenceStatsGenerator();
                sequenceDatabase.getStats(inputFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
