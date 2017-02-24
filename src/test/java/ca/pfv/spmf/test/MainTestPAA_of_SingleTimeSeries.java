package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.timeseries.TimeSeries;
import ca.pfv.spmf.algorithms.timeseries.paa.AlgoPiecewiseAggregateApproximation;
import org.junit.Test;

/**
 * Example of how to calculate the Piecewise Aggregate Approximation of a time series, using
 * the source code of SPMF
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestPAA_of_SingleTimeSeries {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the number of data points that we want as output
            int numberOfSegments = 3;

            // Create a time series
            double[] dataPoints = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
            TimeSeries timeSeries = new TimeSeries(dataPoints, "SERIES1");

            // Applying the  algorithm
            AlgoPiecewiseAggregateApproximation algorithm = new AlgoPiecewiseAggregateApproximation();
            TimeSeries paaTimeSeries = algorithm.runAlgorithm(timeSeries, numberOfSegments);
            algorithm.printStats();

            // Print the PAA
            System.out.println(" Piecewise Aggregation Approximation: ");
            System.out.println(paaTimeSeries.toString());

        });
    }
}
