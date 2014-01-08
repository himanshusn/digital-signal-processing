package cps.lab.utils;

import cps.lab.gui.windows.PlotsTabWindow;
import cps.lab.signal.SimilarityMeasures;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.chart.renderer.xy.YIntervalRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeriesCollection;

import cps.lab.gui.panels.SignalInfoPanel;
import cps.lab.signal.Signal;

public class GraphPanelSeter {

    private static double frequency;

    private static PlotsTabWindow plotsTabWindow = new PlotsTabWindow();

    private static final double SAMPLES = 1000;

    public static void drawGraph(Signal signal, double duration) {

        frequency = SAMPLES / duration;

        Signal graphSignal;
        if (signal.getSignalGenerator() != null) {
            graphSignal = signal.getSignalGenerator().generateSignal(frequency);
        } else {
            graphSignal = signal;
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graphSignal.getSeries());

        JFreeChart chart = null;
        if (Signal.Type.DISCRETE.equals(signal.getType())) {
            chart = ChartFactory.createScatterPlot("XY Chart", // Title
                    "time[s]", // x-axis Label
                    "value", // y-axis Label
                    dataset, // Dataset
                    PlotOrientation.VERTICAL, // Plot Orientation
                    true, // Show Legend
                    true, // Use tooltips
                    false // Configure chart to generate URLs?
            );
        } else {

            chart = ChartFactory.createXYLineChart("XY Chart", // Title
                    "time[s]", // x-axis Label
                    "value", // y-axis Label
                    dataset, // Dataset
                    PlotOrientation.VERTICAL, // Plot Orientation
                    true, // Show Legend
                    true, // Use tooltips
                    false // Configure chart to generate URLs?
            );

        }

        plotsTabWindow.getSignalInfoPanel().updateText(graphSignal);
        plotsTabWindow.setChartPanel(plotsTabWindow.getGraphPanel(), new ChartPanel(chart));
    }

    public static void drawHistogram(Signal signal, int bins) {

        HistogramDataset histogramdataset = new HistogramDataset();
        histogramdataset.setType(HistogramType.FREQUENCY);

        double[] yValues = new double[signal.getValues().size()];
        for (int i = 0; i < yValues.length; i++) {
            yValues[i] = signal.getValues().get(i);
        }

        histogramdataset.addSeries("Histogram", yValues, bins);
        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Number", "Value", histogramdataset,
                PlotOrientation.VERTICAL, false, false, false);

        plotsTabWindow.setChartPanel(plotsTabWindow.getHistogramPanel(), new ChartPanel(chart));
    }

    public static void drawSamples(Signal signal) {

//        ValueAxis domainAxis = new NumberAxis("time[s]");
//        ValueAxis rangeAxis = new NumberAxis("value");
//        domainAxis.setRange(signal.getDomain());
//        rangeAxis.setRange(signal.getRange());

//        YIntervalRenderer renderer = new YIntervalRenderer();

//        XYPlot plot = new XYPlot(signal.getDataset(), domainAxis, rangeAxis, renderer);

//        JFreeChart chart = new JFreeChart("Probkowanie", plot);

        JFreeChart chart = ChartFactory.createScatterPlot("Quantization", // Title
                "time[s]", // x-axis Label
                "value", // y-axis Label
                new XYSeriesCollection(signal.getSeries()),
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false); // Configure chart to generate URLs?)

        plotsTabWindow.setChartPanel(plotsTabWindow.getSamplingPanel(), new ChartPanel(chart));
    }

    public static void drawQuantization(Signal originalSignal, Signal quantizedSignal, SimilarityMeasures similarityMeasures) {

        frequency = SAMPLES / originalSignal.getSignalTime();
        Signal signal = originalSignal.getSignalGenerator().generateSignal(frequency);

        JFreeChart chart = ChartFactory.createXYLineChart("Quantization", // Title
                "time[s]", // x-axis Label
                "value", // y-axis Label
                new XYSeriesCollection(signal.getSeries()),
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        XYStepRenderer xyStepRenderer = new XYStepRenderer();
        chart.getXYPlot().setRenderer(1, xyStepRenderer);
        chart.getXYPlot().setDataset(1, quantizedSignal.getDataset());

        plotsTabWindow.getSignalInfoPanel().updateSimilarityMeasures(similarityMeasures);
        plotsTabWindow.setChartPanel(plotsTabWindow.getQuantizationWindow(), new ChartPanel(chart));
    }

    public static void drawInterpolation(Signal signal, Signal reconstructedSignal, SimilarityMeasures similarityMeasures) {

        JFreeChart chart = ChartFactory.createXYLineChart("Interpolation", // Title
                "time[s]", // x-axis Label
                "value", // y-axis Label
                new XYSeriesCollection(signal.getSeries()), // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        StandardXYItemRenderer standardXYItemRenderer = new StandardXYItemRenderer();
        chart.getXYPlot().setRenderer(1, standardXYItemRenderer);
        chart.getXYPlot().setDataset(1, new XYSeriesCollection(reconstructedSignal.getSeries()));

        plotsTabWindow.getSignalInfoPanel().updateSimilarityMeasures(similarityMeasures);
        plotsTabWindow.setChartPanel(plotsTabWindow.getInterpolationPanel(), new ChartPanel(chart));
    }

    public static void drawFiltering(Signal filteredSignal) {

        JFreeChart chart = ChartFactory.createXYLineChart("Filtering", // Title
                "time[s]", // x-axis Label
                "value", // y-axis Label
                new XYSeriesCollection(filteredSignal.getSeries()), // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        plotsTabWindow.setChartPanel(plotsTabWindow.getFilterPanel(), new ChartPanel(chart));
    }

}