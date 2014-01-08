package cps.lab.gui.windows;

import cps.lab.gui.panels.CharPanelReal;
import cps.lab.gui.panels.SignalInfoPanel;
import cps.lab.signal.Signal;

import javax.swing.*;
import java.awt.*;

/**
 * User: maciek
 * Date: 02.11.13
 * Time: 21:48
 */
public class PlotsTabWindow extends JDialog {

    private JTabbedPane tabbedPane;

    private CharPanelReal graphPanel;
    private CharPanelReal histogramPanel;
    private CharPanelReal samplingPanel;
    private CharPanelReal quantizationWindow;
    private CharPanelReal interpolationPanel;
    private CharPanelReal filterPanel;

    private SignalInfoPanel signalInfoPanel;

    public PlotsTabWindow() {
        super();
        setBounds(150, 100, 1200, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(800, 525));

        graphPanel = new CharPanelReal();
        tabbedPane.addTab("Signal", null, graphPanel, "Signal");

        histogramPanel = new CharPanelReal();
        tabbedPane.addTab("Histogram", null, histogramPanel, "Histogram");

        samplingPanel = new CharPanelReal();
        tabbedPane.addTab("Sampling", null, samplingPanel, "Sampling");

        quantizationWindow = new CharPanelReal();
        tabbedPane.addTab("Quantization", null, quantizationWindow, "Quantization");

        interpolationPanel = new CharPanelReal();
        tabbedPane.addTab("Interpolation", null, interpolationPanel, "Interpolation");

        filterPanel = new CharPanelReal();
        tabbedPane.addTab("Filtering", null, filterPanel, "Filtering");

        getContentPane().add(tabbedPane);

        signalInfoPanel = new SignalInfoPanel();
        signalInfoPanel.setPreferredSize(new Dimension(350, 525));

        getContentPane().add(signalInfoPanel);
        setVisible(true);
    }

    public CharPanelReal getGraphPanel() {
        return graphPanel;
    }

    public CharPanelReal getHistogramPanel() {
        return histogramPanel;
    }

    public CharPanelReal getSamplingPanel() {
        return samplingPanel;
    }

    public CharPanelReal getQuantizationWindow() {
        return quantizationWindow;
    }

    public CharPanelReal getInterpolationPanel() {
        return interpolationPanel;
    }

    public void setChartPanel(CharPanelReal jPanel, JPanel chart) {
        jPanel.setChartPanel(chart);
        jPanel.revalidate();
        jPanel.repaint();
    }

    public CharPanelReal getFilterPanel() {
        return filterPanel;
    }

    public SignalInfoPanel getSignalInfoPanel() {
        return signalInfoPanel;
    }

}