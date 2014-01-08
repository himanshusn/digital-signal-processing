package cps.lab.gui.menu;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;
import cps.lab.utils.GraphPanelSeter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: maciek
 * Date: 30.10.13
 * Time: 20:52
 */
public class SamplingMenuItem extends JMenuItem implements ActionListener {

    public SamplingMenuItem(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double frequency;
        try {

            frequency = Double.parseDouble(JOptionPane.showInputDialog("Enter sampling frequency"));

            SignalGenerator signalGenerator = GeneratorWindow.currentSignal.getSignalGenerator();
            Signal samples = signalGenerator.generateSamples(frequency);
            GeneratorWindow.currentSamples = samples;

            GraphPanelSeter.drawSamples(samples);

        } catch (NumberFormatException exception) {
        }
    }
}
