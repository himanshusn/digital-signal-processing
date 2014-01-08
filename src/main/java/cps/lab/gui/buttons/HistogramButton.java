package cps.lab.gui.buttons;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.utils.GraphPanelSeter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: maciek
 * Date: 18.10.13
 * Time: 22:19
 */
public class HistogramButton extends JButton implements ActionListener {

    public HistogramButton() {
        super("Histogram");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int bins = 5;
        try {
            bins = Integer.parseInt(GeneratorWindow.interval.getText());
        } catch (NumberFormatException e1) {
        } finally {
            GraphPanelSeter.drawHistogram(GeneratorWindow.currentSignal, bins);
        }
    }
}
