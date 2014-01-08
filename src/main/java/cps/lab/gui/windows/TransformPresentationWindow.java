package cps.lab.gui.windows;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import cps.lab.gui.windows.TransformWindow.PresentationType;
import cps.lab.signal.Signal;

public class TransformPresentationWindow extends JFrame{
	
	private Signal transformedSignal;
	private double basePeriod;

	public TransformPresentationWindow(Signal transformedSignal, PresentationType presentationType,
			int fastTransformationTime,int transformationTime, double basePeriod) throws HeadlessException {
		super();
		this.transformedSignal = transformedSignal;
		
		getContentPane().setLayout(new BorderLayout(0, 0));

        setBounds(100, 100, 1024, 550);
        JPanel timeResultPanel = new JPanel();
		getContentPane().add(timeResultPanel, BorderLayout.WEST);
		GridBagLayout gbl_timeResultPanel = new GridBagLayout();
		gbl_timeResultPanel.columnWidths = new int[]{0, 0};
		gbl_timeResultPanel.rowHeights = new int[]{0, 0, 0};
		gbl_timeResultPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_timeResultPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		timeResultPanel.setLayout(gbl_timeResultPanel);
		
		JLabel fastTransformTimeLabel = new JLabel("Fast Transform Time : " + fastTransformationTime );
		GridBagConstraints gbc_fastTransformTimeLabel = new GridBagConstraints();
		gbc_fastTransformTimeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_fastTransformTimeLabel.gridx = 0;
		gbc_fastTransformTimeLabel.gridy = 0;
		timeResultPanel.add(fastTransformTimeLabel, gbc_fastTransformTimeLabel);
		
		JLabel transformTimeLable = new JLabel("Transform Time : " + transformationTime );
		GridBagConstraints gbc_transformTimeLable = new GridBagConstraints();
		gbc_transformTimeLable.gridx = 0;
		gbc_transformTimeLable.gridy = 1;
		timeResultPanel.add(transformTimeLable, gbc_transformTimeLable);
		
		if (presentationType == PresentationType.W1) {
			prepareW1();
		}else{
			prepareW2();
		}
		
	}

	/**
	 *  x_na_osi = m * f_probkowania / N
	 */
	private void prepareW1() {

        double frequency = transformedSignal.getFrequency();
        int N = transformedSignal.getComplexValues().length;

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(transformedSignal.getComplexValues().length);

        for (int i = 0; i < transformedSignal.getComplexValues().length; i++) {
            series.add(i * frequency / N, transformedSignal.getComplexValues()[i].getReal());
        }

        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
                "frequency", // x-axis Label
                "real", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        getContentPane().add(new ChartPanel(chart), BorderLayout.NORTH);

        XYSeriesCollection datasetImaginary = new XYSeriesCollection();
        XYSeries seriesIm = new XYSeries(transformedSignal.getComplexValues().length);

        for (int i = 0; i < transformedSignal.getComplexValues().length; i++) {
            seriesIm.add(i * frequency / N, transformedSignal.getComplexValues()[i].getImaginary());
        }

        datasetImaginary.addSeries(seriesIm);

        JFreeChart chartIm = ChartFactory.createXYLineChart("XY Chart", // Title
                "frequency", // x-axis Label
                "imagenery", // y-axis Label
                datasetImaginary, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        getContentPane().add(new ChartPanel(chartIm), BorderLayout.SOUTH);
        
        this.revalidate();
    }

	private void prepareW2(){
		double frequency = transformedSignal.getFrequency();
        int N = transformedSignal.getComplexValues().length;

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(transformedSignal.getComplexValues().length);

        for (int i = 0; i < transformedSignal.getComplexValues().length; i++) {
            series.add(i, transformedSignal.getComplexValues()[i].abs());
        }

        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
                "i", // x-axis Label
                "abs", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        getContentPane().add(new ChartPanel(chart), BorderLayout.NORTH);

        XYSeriesCollection datasetImaginary = new XYSeriesCollection();
        XYSeries seriesIm = new XYSeries(transformedSignal.getComplexValues().length);

        for (int i = 0; i < transformedSignal.getComplexValues().length; i++) {
            seriesIm.add(i * frequency / N, transformedSignal.getComplexValues()[i].getArgument());
        }

        datasetImaginary.addSeries(seriesIm);

        JFreeChart chartIm = ChartFactory.createXYLineChart("XY Chart", // Title
                "frequency", // x-axis Label
                "argument", // y-axis Label
                datasetImaginary, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        getContentPane().add(new ChartPanel(chartIm), BorderLayout.SOUTH);
	}
	
	

}
