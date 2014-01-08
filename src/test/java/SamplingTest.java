/**
 * User: maciek
 * Date: 30.10.13
 * Time: 06:14
 */

import cps.lab.signal.Signal;
import cps.lab.signal.generator.*;
import cps.lab.signal.generator.base.SignalGenerator;
import cps.lab.utils.SignalUtils;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.util.Collections;

/**
 * @author Roger Studner
 */
public class SamplingTest {

    private ChartFrame frame = null;

    private void displayChart() {

        SignalGenerator signalGenerator = new SinusoidalGenerator(2, 10, 1, 1);
        //        SignalGenerator signalGenerator = new NormalDistributionGenerator(2, 10, 0);
        //        SignalGenerator signalGenerator = new UnitStepGenerator(2, 10, 1, 5);
        //        SignalGenerator signalGenerator = new RectangularGenerator(2, 10, 1, 3, 1);

        Signal signal = signalGenerator.generateSignal(10);

        // ten sygnal jest typy SAMPLE - enum Signal.Type
        Signal samples = signalGenerator.generateSamples(10);

        // ten sygnal jest typu QUANITZED - enum Signal.Type
        Signal quantizedSignal = SignalUtils.quantization(2, samples);

        NumberAxis xAxis = new NumberAxis("time[s]");
        NumberAxis yAxis = new NumberAxis("value");

        XYStepRenderer xyStepRenderer = new XYStepRenderer();

        XYPlot plot = new XYPlot(quantizedSignal.getDataset(), xAxis, yAxis, xyStepRenderer);

        JFreeChart chart = new JFreeChart("Kwantyzacja równomierna z obcięciem", plot);

        this.frame = new ChartFrame("Plan Comparison", chart);
        this.frame.pack();
        RefineryUtilities.positionFrameRandomly(this.frame);
        this.frame.setVisible(true);

    }

    public static void main(final String[] args) {

        final SamplingTest demo = new SamplingTest();
        demo.displayChart();

    }

}