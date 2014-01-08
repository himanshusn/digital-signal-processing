import cps.lab.fillter.BandPassFilter;
import cps.lab.fillter.windows.BlackmanWindow;
import cps.lab.fillter.Filter;
import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;
import cps.lab.signal.operation.DiscreteFourierTransform;
import cps.lab.utils.SignalUtils;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;


public class FilteringTest {

    public static void main(String[] args) {

        /**
         * Generowanie odpowiedzi impulsowej
         */
        Filter filter = new BandPassFilter(63, 8, 256, new BlackmanWindow()).build();
        Complex[] impulseResponse = filter.getImpulseResponse();

        /**
         * Obliczenie widm transmitancji filtru
         */
        Signal signal = new Signal();
        signal.setComplexValues(impulseResponse);
        Complex[] bes = new DiscreteFourierTransform().transform(signal).getComplexValues();


        JFrame charTest = new JFrame();
        charTest.setBounds(100, 100, 800, 300);

        XYSeries series = new XYSeries("XYGraph");
        for (int t = 0; t < impulseResponse.length; t++) {
            series.add(t, impulseResponse[t].getReal());
        }

        XYSeries series2 = new XYSeries("XYGraph");
        for (int i = 0; i < bes.length; i++) {
            /**
             * modul transmitancji = sqrt(x^2 + y^2) w liczbach zespolonych
             */
            series2.add(i, bes[i].abs());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        XYSeriesCollection dataset2 = new XYSeriesCollection();
        dataset2.addSeries(series2);

        XYSplineRenderer spline = new XYSplineRenderer();
        spline.setSeriesShapesVisible(0, false);

        NumberAxis xax = new NumberAxis("x");
        NumberAxis yax = new NumberAxis("y");

        XYPlot plot = new XYPlot(dataset, xax, yax, spline);
        plot.setDataset(2, dataset2);

        JFreeChart chart = new JFreeChart(plot);
        charTest.add(new ChartPanel(chart), BorderLayout.CENTER);
        charTest.setVisible(true);

    }

}
