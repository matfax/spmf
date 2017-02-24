package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.tools.other_dataset_tools.ResizeDatabaseTool;
import org.junit.Test;

/**
 * Class file that shows how to resize a database
 * using the tool called "ResizeDatabaseTool".
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestResizeDatabaseTool {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // input file path
            String input = "DB_UtilityPerHUIs.txt";
            // output file path
            String output = ".//output.txt";

            // percentage of database to use  (e.g. 75 %)
            double percentage = 0.7;

            // Create the tool to resize a database
            ResizeDatabaseTool resizer = new ResizeDatabaseTool();
            // Run the algorithm
            resizer.convert(input, output, percentage);
        });
    }
}
