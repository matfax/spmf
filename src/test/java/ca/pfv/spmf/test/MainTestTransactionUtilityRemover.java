package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.other_dataset_tools.TransactionUtilityRemover;
import org.junit.Test;

/**
 * Class file that shows how to remove the utility value from a transaction database having utility values
 * using the tool called "TransactionUtilityRemover".
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestTransactionUtilityRemover {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // input file path
            String input = "DB_Utility.txt";
            // output file path
            String output = ".//output.txt";

            // Create the tool to remove utility values
            TransactionUtilityRemover remover = new TransactionUtilityRemover();
            // Run the algorithm
            remover.convert(input, output);
            // The result will be saved to the output file path
        });
    }
}
