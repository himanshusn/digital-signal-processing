import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.SinusoidalGenerator;



public class JFreeCharTest extends JFrame {
	
	public static void main(String args[]){
		JFreeCharTest charTest = new JFreeCharTest();
		charTest.add(charTest.testXYSeriesChar(), BorderLayout.CENTER);
		charTest.setVisible(true);
	}
	
	ChartPanel testXYSeriesChar(){
		
		 Signal signal = new SinusoidalGenerator(1, 10, 0, 10).generateSignal(10);
//		 Signal signal = SignalGenerator.generateSinusoidalFullRectified(1, 100, 10, 0, 10);
		
		 XYSeries series = signal.getSeries();//new XYSeries("XYGraph");
//		 series.add(1, 1);
//		 series.add(1, 2);
//		 series.add(2, 1);
//		 series.add(3, 9);
//		 series.add(4, 10);
		 // Add the series to your data set
		 XYSeriesCollection dataset = new XYSeriesCollection();
		 dataset.addSeries(series);
		 
		 JFreeChart chart = ChartFactory.createScatterPlot(
		 "XY Chart", // Title
		 "x-axis", // x-axis Label
		 "y-axis", // y-axis Label
		 dataset, // Dataset
		 PlotOrientation.VERTICAL,
		 true, // Show Legend
		 true,
		 true// Use tooltips
		 );
		 
		 return new ChartPanel(chart);
	}
	
}
