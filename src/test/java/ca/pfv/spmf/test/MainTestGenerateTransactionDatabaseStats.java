package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.dataset_stats.TransactionStatsGenerator;
import org.junit.Test;

/**
 * Example of how to get statistics about a transaction database in SPMF format
 */
public class MainTestGenerateTransactionDatabaseStats {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            String inputFile = "contextPasquier99.txt";
            try {
                TransactionStatsGenerator transDBStats = new TransactionStatsGenerator();
                transDBStats.getStats(inputFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
