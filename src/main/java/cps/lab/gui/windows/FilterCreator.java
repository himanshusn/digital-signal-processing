package cps.lab.gui.windows;

import cps.lab.fillter.BandPassFilter;
import cps.lab.fillter.Filter;
import cps.lab.fillter.LowPassFilter;
import cps.lab.fillter.windows.BlackmanWindow;
import cps.lab.fillter.windows.RectangularWindow;
import cps.lab.signal.Signal;
import cps.lab.signal.operation.DiscreteFourierTransform;
import cps.lab.utils.GraphPanelSeter;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class FilterCreator extends JFrame implements ActionListener {

    private JPanel contentPane;

    private JTextField impulsResponseNumberText;
    private JTextField cuttingFrequencyText;

    private JComboBox filterTypeCBox;
    private JComboBox windowMethodCBox;

    private String[] filterTypes = new String[]{"Low-pass filter", "Band-pass filter"};
    private String[] windowTypes = new String[]{"Rectangular window", "Blackman window"};
    private JLabel lblImpulsResponses;
    private JLabel lblCuttingFrequency;

    /**
	 * Create the frame.
	 */
	public FilterCreator() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{117, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblImpulsResponses = new JLabel("Impuls Responses");
		GridBagConstraints gbc_lblImpulsResponses = new GridBagConstraints();
		gbc_lblImpulsResponses.insets = new Insets(0, 0, 5, 5);
		gbc_lblImpulsResponses.gridx = 0;
		gbc_lblImpulsResponses.gridy = 2;
		contentPane.add(lblImpulsResponses, gbc_lblImpulsResponses);

		impulsResponseNumberText = new JTextField();
		GridBagConstraints gbc_impulsResponseNumberText = new GridBagConstraints();
		gbc_impulsResponseNumberText.insets = new Insets(0, 0, 5, 0);
		gbc_impulsResponseNumberText.fill = GridBagConstraints.HORIZONTAL;
		gbc_impulsResponseNumberText.gridx = 1;
		gbc_impulsResponseNumberText.gridy = 2;
		contentPane.add(impulsResponseNumberText, gbc_impulsResponseNumberText);
		impulsResponseNumberText.setColumns(10);
		
		lblCuttingFrequency = new JLabel("Cutting frequency");
		GridBagConstraints gbc_lblCuttingFrequency = new GridBagConstraints();
		gbc_lblCuttingFrequency.insets = new Insets(0, 0, 5, 5);
		gbc_lblCuttingFrequency.gridx = 0;
		gbc_lblCuttingFrequency.gridy = 3;
		contentPane.add(lblCuttingFrequency, gbc_lblCuttingFrequency);

		cuttingFrequencyText = new JTextField();
		GridBagConstraints gbc_cuttingFrequencyText = new GridBagConstraints();
		gbc_cuttingFrequencyText.insets = new Insets(0, 0, 5, 0);
		gbc_cuttingFrequencyText.fill = GridBagConstraints.HORIZONTAL;
		gbc_cuttingFrequencyText.gridx = 1;
		gbc_cuttingFrequencyText.gridy = 3;
		contentPane.add(cuttingFrequencyText, gbc_cuttingFrequencyText);
		cuttingFrequencyText.setColumns(10);

        filterTypeCBox = new JComboBox(filterTypes);
        GridBagConstraints gbc_filterTypeCBox = new GridBagConstraints();
        gbc_filterTypeCBox.insets = new Insets(0, 0, 5, 0);
        gbc_filterTypeCBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_filterTypeCBox.gridx = 1;
        gbc_filterTypeCBox.gridy = 5;
        contentPane.add(filterTypeCBox, gbc_filterTypeCBox);

        windowMethodCBox = new JComboBox(windowTypes);
        GridBagConstraints gbc_windowMethodCBox = new GridBagConstraints();
		gbc_windowMethodCBox.insets = new Insets(0, 0, 5, 0);
		gbc_windowMethodCBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_windowMethodCBox.gridx = 1;
		gbc_windowMethodCBox.gridy = 6;
        contentPane.add(windowMethodCBox, gbc_windowMethodCBox);

        JButton filteringButton = new JButton("Filtering");
        filteringButton.addActionListener(this);
        GridBagConstraints gbc_filteringButton = new GridBagConstraints();
        gbc_filteringButton.gridx = 1;
        gbc_filteringButton.gridy = 8;
        contentPane.add(filteringButton, gbc_filteringButton);

	}

    @Override
    public void actionPerformed(ActionEvent e) {

        int impulseNumber = Integer.parseInt(impulsResponseNumberText.getText());
        double cuttingFrequency = Double.parseDouble(cuttingFrequencyText.getText());
        int N = 256;

        Signal currentSignal = GeneratorWindow.currentSamples;
        double K = currentSignal.getFrequency() / cuttingFrequency;

        String filterType = (String) filterTypeCBox.getSelectedItem();
        String windowType = (String) windowMethodCBox.getSelectedItem();

        Filter filter = null;
        if (filterType.equals(filterTypes[0])) {

            filter = new LowPassFilter(impulseNumber, K, N, null);

        } else if (filterType.equals(filterTypes[1])) {

            filter = new BandPassFilter(impulseNumber, K, N, null);

        }

        if (windowType.equals(windowTypes[0])) {

            filter.setWindowFunction(new RectangularWindow());

        } else if (windowType.equals(windowTypes[1])) {

            filter.setWindowFunction(new BlackmanWindow());

        }

        filter = filter.build();
        GeneratorWindow.currentFilter = filter;
        //todo narysowac filtr i jego dft
        JFrame charTest = new JFrame("Filter");
        charTest.setBounds(100, 100, 800, 300);

        XYSeries series = new XYSeries("filter response");
        for (int t = 0; t < filter.getImpulseResponse().length; t++) {
            series.add(t, filter.getImpulseResponse()[t].getReal());
        }

        Signal signal = new Signal();
        signal.setComplexValues(filter.getImpulseResponse());
        Complex[] bes = new DiscreteFourierTransform().transform(signal).getComplexValues();
        XYSeries series2 = new XYSeries("dft of filter response");
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
        StandardXYItemRenderer standardXYItemRenderer = new StandardXYItemRenderer();
        plot.setRenderer(1, standardXYItemRenderer);
        plot.setDataset(1, dataset2);

        JFreeChart chart = new JFreeChart(plot);
        charTest.getContentPane().add(new ChartPanel(chart), BorderLayout.CENTER);
        charTest.setVisible(true);


        Signal filteredSignal = null;
        try {
            filteredSignal = filter.filter(currentSignal);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        GraphPanelSeter.drawFiltering(filteredSignal);

    }
}
