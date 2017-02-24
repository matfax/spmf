package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.frequentpatterns.hui_miner.AlgoHUI_LIST_INS;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Example of how to use the HUI-LIST-INS algorithm from the source code by processing a single file
 * in several parts (updates).
 *
 * @author Philippe Fournier-Viger, 2014
 */
public class MainTestHUI_LIST_INS_Xupdates {

    public static int countLines(String filepath) throws IOException {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(MainTestHUI_LIST_INS_Xupdates.class.getClassLoader().getResourceAsStream(filepath)));
        while (reader.readLine() != null) {
        }
        int count = reader.getLineNumber();
        reader.close();
        return count;
    }

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

                    String input = "DB_Utility.txt"; // retails_utilityPFV
                    int min_utility = 30;

                    // the number of updates to be performed
                    int numberOfUpdates = 2;

                    // scan the database to count the number of lines
                    // for our test purpose
                    int linecount = countLines(input);

                    double addedratio = 1d / ((double) numberOfUpdates);
                    int linesForeEachUpdate = (int) (addedratio * linecount);

                    // output file path
                    String output = ".//output.txt";

                    // Apply the algorithm several times
                    AlgoHUI_LIST_INS algo = new AlgoHUI_LIST_INS();
                    int firstLine = 0;
                    for (int i = 0; i < numberOfUpdates; i++) {
                        int lastLine = firstLine + linesForeEachUpdate;
                        //

                        // Applying the algorithm
                        // If this is the last update, we make sure to run until the last line
                        if (i == numberOfUpdates - 1) {
                            System.out.println("" + i + ") Run the algorithm using line " + firstLine + " to before line " + linecount + " of the input database.");
                            algo.runAlgorithm(input, output, min_utility, firstLine, linecount);
                        } else {
                            // If this is not the last update
                            System.out.println("" + i + ") Run the algorithm using line " + firstLine + " to before line " + lastLine + " of the input database.");
                            algo.runAlgorithm(input, output, min_utility, firstLine, lastLine);
                        }
                        algo.printStats();

                        firstLine = lastLine;
                    }
                }

                /**
                 * This methods counts the number of lines in a text file.
                 * @param filepath the path to the file
                 * @return the number of lines as an int
                 * @throws IOException Exception if error reading/writting file
                 */

        );
    }
}
