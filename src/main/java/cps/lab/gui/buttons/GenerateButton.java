package cps.lab.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.signal.generator.ImpulseNoiseGenerator;
import cps.lab.signal.generator.NormalDistributionGenerator;
import cps.lab.signal.generator.RectangularGenerator;
import cps.lab.signal.generator.RectangularSymmetricGenerator;
import cps.lab.signal.generator.S3Generator;
import cps.lab.signal.generator.SinusoidalFRGenerator;
import cps.lab.signal.generator.SinusoidalGenerator;
import cps.lab.signal.generator.SinusoidalHRGenerator;
import cps.lab.signal.generator.TriangularGenerator;
import cps.lab.signal.generator.UniformDistribution;
import cps.lab.signal.generator.UnitImpulseGenerator;
import cps.lab.signal.generator.UnitStepGenerator;
import cps.lab.signal.generator.base.SignalGenerator;
import cps.lab.utils.GraphPanelSeter;

public class GenerateButton extends JButton implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1653865063269280489L;

    private JComboBox<String> combobox;
    private List<JTextField> textFieldList;
    private List<SignalGenerator> signalGeneratorList;
    private final double FREQUENCY = 150;

    public GenerateButton(String text, JComboBox<String> combobox, List<JTextField> signal) {
        super(text);
        this.combobox = combobox;
        this.textFieldList = signal;
        this.addActionListener(this);
        this.signalGeneratorList = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = combobox.getSelectedIndex();
        double A = 1;
        double d = 0;
        double t1 = 0;
        double T = 0;
        double kw = 0;
        int ns = 0;
        double p = 0;
        double ts = 0;

        try {
            A = Double.parseDouble(textFieldList.get(0).getText());
        } catch (NumberFormatException exception) {
        }

        try {
            d = Double.parseDouble(textFieldList.get(1).getText());
        } catch (NumberFormatException exception) {
        }

        try {
            t1 = Double.parseDouble(textFieldList.get(2).getText());
        } catch (NumberFormatException exception) {
        }

        try {
            T = Double.parseDouble(textFieldList.get(3).getText());
        } catch (NumberFormatException exception) {
        }

        try {
            kw = Double.parseDouble(textFieldList.get(4).getText());
        } catch (NumberFormatException exception) {
        }

        try {
            ns = Integer.parseInt(textFieldList.get(5).getText());
        } catch (NumberFormatException exception) {
        }

        try {
            p = Double.parseDouble(textFieldList.get(6).getText());
        } catch (NumberFormatException exception) {
        }

        try {
            ts = Double.parseDouble(textFieldList.get(7).getText());
        } catch (NumberFormatException exception) {
        }

        setSignalGeneratorList(A, d, t1, T, kw, ns, p, ts);
        generateSignal(signalGeneratorList.get(index), index);
    }

    private void setSignalGeneratorList(double a, double d, double t1, double t, double kw, int ns, double p, double ts) {
        signalGeneratorList.clear();
        signalGeneratorList.add(new SinusoidalGenerator(a, d, t1, t));
        signalGeneratorList.add(new UniformDistribution(a, d, t1));
        signalGeneratorList.add(new NormalDistributionGenerator(a, d, t1));
        signalGeneratorList.add(new RectangularGenerator(a, d, t1, t, kw));
        signalGeneratorList.add(new SinusoidalFRGenerator(a, d, t1, t));
        signalGeneratorList.add(new SinusoidalHRGenerator(a, d, t1, t));
        signalGeneratorList.add(new UnitImpulseGenerator(a, d, t1, ns));
        signalGeneratorList.add(new UnitStepGenerator(a, d, t1, ts));
        signalGeneratorList.add(new ImpulseNoiseGenerator(a, d, t1, p));
        signalGeneratorList.add(new RectangularSymmetricGenerator(a, d, t1, t, kw));
        signalGeneratorList.add(new TriangularGenerator(a, d, t1, t, kw));
        signalGeneratorList.add(new S3Generator(t1,d));

    }

    private void generateSignal(SignalGenerator signalGenerator, int index) {
        GeneratorWindow.currentSignal = signalGenerator.generateSignal(FREQUENCY);
        GraphPanelSeter.drawGraph(GeneratorWindow.currentSignal, GeneratorWindow.currentSignal.getSignalTime());
    }
}
