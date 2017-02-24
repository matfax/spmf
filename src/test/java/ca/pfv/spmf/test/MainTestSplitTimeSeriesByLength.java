package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.timeseries.TimeSeries;
import ca.pfv.spmf.algorithms.timeseries.split.AlgoSplitTimeSeries;
import org.junit.Test;

/**
 * Example of how to split a time-series by length using
 * the source code of SPMF
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestSplitTimeSeriesByLength {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the number of data points that we want per time series
            int sizeOfSegment = 3;

            // Create a time series
            double[] dataPoints = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
            TimeSeries timeSeries = new TimeSeries(dataPoints, "SERIES1");

            // Applying the  algorithm
            AlgoSplitTimeSeries algorithm = new AlgoSplitTimeSeries();
            TimeSeries[] splittedTimeSeries = algorithm.runAlgorithm(timeSeries, sizeOfSegment);
            algorithm.printStats();

            // Print the sax sequences
            System.out.println(" Splitted time series: ");
            for (int i = 0; i < splittedTimeSeries.length; i++) {
                System.out.println("Time series " + i + " " + splittedTimeSeries[i]);
            }

        });
    }
}
