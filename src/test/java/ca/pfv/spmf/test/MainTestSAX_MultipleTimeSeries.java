package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.timeseries.TimeSeries;
import ca.pfv.spmf.algorithms.timeseries.sax.AlgoSAX;
import ca.pfv.spmf.algorithms.timeseries.sax.SAXSymbol;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Example of how to use SAX algorithm for converting multiple time series to the SAX representation, in the source code
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestSAX_MultipleTimeSeries {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            int numberOfSegments = 2;
            int numberOfSymbols = 3;

            // Create a time series
            List<TimeSeries> timeSeries = new ArrayList<TimeSeries>();
            TimeSeries timeSeries1 = new TimeSeries(
                    new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0},
                    "SERIES1");
            TimeSeries timeSeries2 = new TimeSeries(
                    new double[]{10, 9, 8, 7, 6, 5},
                    "SERIES2");
            TimeSeries timeSeries3 = new TimeSeries(
                    new double[]{-1, -2, -3, -4, -5},
                    "SERIES3");
            TimeSeries timeSeries4 = new TimeSeries(
                    new double[]{-1.0, -2.0, -3.0, -4.0, -5.0},
                    "SERIES4");

            // Set this variable to true to deactivate the PAA part of the SAX algorithm.
            boolean deactivatePAA = false;

            timeSeries.add(timeSeries1);
            timeSeries.add(timeSeries2);
            timeSeries.add(timeSeries3);
            timeSeries.add(timeSeries4);

            // Applying the  algorithm
            AlgoSAX algorithm = new AlgoSAX();
            SAXSymbol[][] saxSequences = algorithm.runAlgorithm(timeSeries, numberOfSegments, numberOfSymbols, deactivatePAA);
            algorithm.printStats();

            // Print the list of SAX symbols
            SAXSymbol[] symbols = algorithm.getSymbols();
            System.out.println(" SAX SYMBOLS: ");
            System.out.println(" Symbols : " + Arrays.toString(symbols) + System.lineSeparator());

            // Print the sax sequences
            System.out.println(" SAX SEQUENCES : ");
            for (SAXSymbol[] saxSequence : saxSequences) {
                System.out.println(" Sequence : " + Arrays.toString(saxSequence));
            }
        });
    }
}
