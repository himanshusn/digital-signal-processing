package cps.lab.gui.menu;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.signal.SimilarityMeasures;
import cps.lab.utils.GraphPanelSeter;
import cps.lab.utils.SignalUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * User: maciek
 * Date: 30.10.13
 * Time: 20:52
 */
public class QuantizationMenuItem extends JMenuItem implements ActionListener {

    public QuantizationMenuItem(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int bitsNumber;
        try {
            bitsNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter number of bits representation"));

            if (GeneratorWindow.currentSamples != null) {
                GeneratorWindow.currentQuantization = SignalUtils.quantization(bitsNumber, GeneratorWindow.currentSamples);

                List<Double> values = GeneratorWindow.currentSamples.getValues();
                List<Double> reconstructedValues = GeneratorWindow.currentQuantization.getValues();

                SimilarityMeasures similarityMeasures = new SimilarityMeasures().build(values, reconstructedValues);

                GraphPanelSeter.drawQuantization(GeneratorWindow.currentSignal, GeneratorWindow.currentQuantization, similarityMeasures);
            }

        } catch (NumberFormatException exception) {
        }
    }
}
