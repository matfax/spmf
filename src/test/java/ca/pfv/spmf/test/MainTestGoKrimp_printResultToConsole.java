package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.sequentialpatterns.goKrimp.AlgoGoKrimp;
import ca.pfv.spmf.algorithms.sequentialpatterns.goKrimp.DataReader;
import org.junit.Test;

/**
 * Example of how to use the GoKrimp Algorithm in source code and print
 * the result to the console.
 */
public class MainTestGoKrimp_printResultToConsole {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {
            String inputDatabase = "test_goKrimp.dat"; // the database
//		String inputLabelFile = "test_goKrimp.lab"; // the label file
            String inputLabelFile = "";  // use this if no label file

            DataReader d = new DataReader();
            //GoKrimp g=d.readData(inputDatabase, inputLabelFile);
            AlgoGoKrimp g = d.readData_SPMF(inputDatabase, inputLabelFile);
            //g.printData();
            g.gokrimp();

        });
    }
}
