package cps.lab.gui.menu;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.signal.Signal;
import cps.lab.signal.SimilarityMeasures;
import cps.lab.signal.generator.base.SignalGenerator;
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
public class InterpolationMenuItem extends JMenuItem implements ActionListener {

    public InterpolationMenuItem(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            Object[] inputs = {"Sampling", "Quantization"};
            String in = (String) JOptionPane.showInputDialog(this, "Choose the input", "Input type",
                    JOptionPane.PLAIN_MESSAGE, null, inputs, inputs[0]);

            Signal input;
            if (inputs[0].equals(in)) {
                input = GeneratorWindow.currentSamples;
            } else {
                input = GeneratorWindow.currentQuantization;
            }

            if (input != null) {
                double frequency = Double.parseDouble(JOptionPane.showInputDialog("Enter sampling frequency"));

                Object[] methods = {"Sinc function", "First Order Hold"};
                String meth = (String) JOptionPane.showInputDialog(this, "Choose the interpolation method",
                        "Interpolation method", JOptionPane.PLAIN_MESSAGE, null, methods, methods[0]);

                Signal reconstructedSignal;
                if (methods[0].equals(meth)) {
                    reconstructedSignal = SignalUtils.sincFunctionReconstruction(frequency, input);
                } else {
                    reconstructedSignal = SignalUtils.firstOrderHold(frequency, input);
                }

                SignalGenerator sg = GeneratorWindow.currentSignal.getSignalGenerator();
                Signal originalSignal = sg.generateSignal(reconstructedSignal.getFrequency());

                List<Double> values = originalSignal.getValues();
                List<Double> reconstructedValues = reconstructedSignal.getValues();

                SimilarityMeasures similarityMeasures = new SimilarityMeasures().build(values, reconstructedValues);

                GraphPanelSeter.drawInterpolation(originalSignal, reconstructedSignal, similarityMeasures);
            }
        } catch (NumberFormatException exception) {
        }
    }
}
