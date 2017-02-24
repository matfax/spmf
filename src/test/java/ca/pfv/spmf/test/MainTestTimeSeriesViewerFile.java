package ca.pfv.spmf.test;

import ca.pfv.spmf.NoExceptionAssertion;
import ca.pfv.spmf.algorithms.timeseries.TimeSeries;
import ca.pfv.spmf.algorithms.timeseries.reader_writer.AlgoTimeSeriesReader;
import ca.pfv.spmf.gui.timeseriesviewer.TimeSeriesViewer;
import org.junit.Test;

import java.util.List;

/**
 * Example of how to view a time series, from the source code of SPMF.
 *
 * @author Philippe Fournier-Viger, 2016.
 */
public class MainTestTimeSeriesViewerFile {

    @Test
    public void main() {
        NoExceptionAssertion.assertDoesNotThrow(() -> {

            // the input file
            String input = "contextSAX.txt";   // contextSAX_SplitLength3  //

            // Parameters of the algorithm
            String separator = ",";

            AlgoTimeSeriesReader reader = new AlgoTimeSeriesReader();
            List<TimeSeries> timeSeries = reader.runAlgorithm(input, separator);

            TimeSeriesViewer viewer = new TimeSeriesViewer(timeSeries);
            viewer.setVisible(true);
        });
    }
}
