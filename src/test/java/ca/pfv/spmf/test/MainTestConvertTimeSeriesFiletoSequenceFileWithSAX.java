package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.timeseries.TimeSeries;
import ca.pfv.spmf.algorithms.timeseries.reader_writer.AlgoTimeSeriesReader;
import ca.pfv.spmf.algorithms.timeseries.sax.AlgoConvertTimeSeriesFileToSequencesWithSAX;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to use SAX algorithm for converting a time series from the source code.
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestConvertTimeSeriesFiletoSequenceFileWithSAX {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input file
            String input = "contextSAX.txt";

            // Parameters of the algorithm
            String separator = ",";

            // Applying the  algorithm
            AlgoTimeSeriesReader reader = new AlgoTimeSeriesReader();
            List<TimeSeries> timeSeries = reader.runAlgorithm(input, separator);
            reader.printStats();

            // the output file
            String output = ".//output.txt";

            // Parameters of the algorithm
            int numberOfSegments = 4;
            int numberOfSymbols = 4;

            // Applying the  algorithm
            AlgoConvertTimeSeriesFileToSequencesWithSAX algorithm = new AlgoConvertTimeSeriesFileToSequencesWithSAX();
            algorithm.runAlgorithm(timeSeries, output, numberOfSegments, numberOfSymbols, false);
            algorithm.printStats();
        });
    }
}
