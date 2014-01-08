package cps.lab.gui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cps.lab.fillter.Filter;
import cps.lab.gui.buttons.DataDisplayButton;
import cps.lab.gui.buttons.GenerateButton;
import cps.lab.gui.buttons.HistogramButton;
import cps.lab.gui.buttons.IntervalSlider;
import cps.lab.gui.menu.*;
import cps.lab.signal.Signal;

public class GeneratorWindow extends JFrame {

    private static final long serialVersionUID = -5268611789894395427L;

    public static Signal currentSignal;
    public static Signal currentSamples;
    public static Signal currentQuantization;
    public static Filter currentFilter;

    private JPanel contentPane;
    private JTextField amplitudeTextValue = new JTextField();
    private JTextField signalTimeTextValue = new JTextField();
    private JTextField startTimeTextValue = new JTextField();
    private JTextField basePeriodTextValue = new JTextField();
    private JTextField fillFactorTextValue = new JTextField();
    private List<JTextField> textFieldList;
    public static JTextField interval = new JTextField(5);
    private JTextField stepSampleNrText = new JTextField();
    private JTextField possibilityText = new JTextField();
    private JTextField stepTimeText = new JTextField();

    private Map<String, boolean[]> fieldsRule;
    private List<JTextField> availableFields;

    /**
     * Create the frame.
     */
    public GeneratorWindow() {
        {
            availableFields = Arrays.asList(amplitudeTextValue, signalTimeTextValue, startTimeTextValue, basePeriodTextValue,
                    fillFactorTextValue, stepSampleNrText, possibilityText, stepTimeText);

            fieldsRule = new HashMap<>();
            fieldsRule.put("Sin", new boolean[]{true, true, true, true, false, false, false, false});
            fieldsRule.put("UniformDistribution", new boolean[]{true, true, true, false, false, false, false, false});
            fieldsRule.put("NormalDistribution", new boolean[]{true, true, true, false, false, false, false, false});
            fieldsRule.put("Rectangle", new boolean[]{true, true, true, true, true, false, false, false});
            fieldsRule.put("SinFR", new boolean[]{true, true, true, true, false, false, false, false});
            fieldsRule.put("SinHR", new boolean[]{true, true, true, true, false, false, false, false});
            fieldsRule.put("UnitImpulse", new boolean[]{true, true, true, false, false, true, false, false});
            fieldsRule.put("UnitStep", new boolean[]{true, true, true, false, false, false, false, true});
            fieldsRule.put("ImpulseNoise", new boolean[]{true, true, true, false, false, false, false, false});
            fieldsRule.put("RectangularSymmetric", new boolean[]{true, true, true, true, true, false, false, false});
            fieldsRule.put("Triangular", new boolean[]{true, true, true, true, true, false, false, false});
            fieldsRule.put("S3", new boolean[]{false, true, true, false, false, false, false, false});
        }

        setTitle("Signal Processing");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 445);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmSave = new SaveMenuItem("Save");
        mnFile.add(mntmSave);

        JMenuItem mntmLoad = new LoadMenuItem("Load");
        mnFile.add(mntmLoad);

        JMenu mnSignalOperation = new JMenu("Signal operation");
        menuBar.add(mnSignalOperation);

        JMenuItem mntmSignalOperation = new SignalOperationMenuItem("Signal operation");
        mnSignalOperation.add(mntmSignalOperation);

        mnSignalOperation.addSeparator();

        JMenuItem mntmSampling = new SamplingMenuItem("Sampling");
        mnSignalOperation.add(mntmSampling);
        
                mnSignalOperation.addSeparator();

        JMenuItem mntmQuantization = new QuantizationMenuItem("Quantization");
        mnSignalOperation.add(mntmQuantization);

        JMenuItem mntmInterpolation = new InterpolationMenuItem("Interpolation");
        mnSignalOperation.add(mntmInterpolation);
        
        mnSignalOperation.addSeparator();
        
        JMenuItem mntmTransform = new TransformMenuItem("Transform");
        mnSignalOperation.add(mntmTransform);

        JMenu mnFiltering = new JMenu("Filtering");
        menuBar.add(mnFiltering);

        JMenuItem mntmFilterDesign = new FilteringMenuItem("Create filter");
        mnFiltering.add(mntmFilterDesign);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{72, 0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblAmplitude = new JLabel("Amplitude");
        GridBagConstraints gbc_lblAmplitude = new GridBagConstraints();
        gbc_lblAmplitude.insets = new Insets(0, 0, 5, 5);
        gbc_lblAmplitude.gridx = 0;
        gbc_lblAmplitude.gridy = 3;
        contentPane.add(lblAmplitude, gbc_lblAmplitude);

        GridBagConstraints gbc_amplitudeTextValue = new GridBagConstraints();
        gbc_amplitudeTextValue.insets = new Insets(0, 0, 5, 0);
        gbc_amplitudeTextValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_amplitudeTextValue.gridx = 3;
        gbc_amplitudeTextValue.gridy = 3;
        contentPane.add(amplitudeTextValue, gbc_amplitudeTextValue);
        amplitudeTextValue.setColumns(10);

        JLabel lblSignalTime = new JLabel("Signal time");
        GridBagConstraints gbc_lblSignalTime = new GridBagConstraints();
        gbc_lblSignalTime.insets = new Insets(0, 0, 5, 5);
        gbc_lblSignalTime.gridx = 0;
        gbc_lblSignalTime.gridy = 4;
        contentPane.add(lblSignalTime, gbc_lblSignalTime);

        GridBagConstraints gbc_signalTimeTextValue = new GridBagConstraints();
        gbc_signalTimeTextValue.insets = new Insets(0, 0, 5, 0);
        gbc_signalTimeTextValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_signalTimeTextValue.gridx = 3;
        gbc_signalTimeTextValue.gridy = 4;
        contentPane.add(signalTimeTextValue, gbc_signalTimeTextValue);
        signalTimeTextValue.setColumns(10);

        JLabel lblStartTime = new JLabel("Start time");
        GridBagConstraints gbc_lblStartTime = new GridBagConstraints();
        gbc_lblStartTime.insets = new Insets(0, 0, 5, 5);
        gbc_lblStartTime.gridx = 0;
        gbc_lblStartTime.gridy = 5;
        contentPane.add(lblStartTime, gbc_lblStartTime);

        GridBagConstraints gbc_startTimeTextValue = new GridBagConstraints();
        gbc_startTimeTextValue.insets = new Insets(0, 0, 5, 0);
        gbc_startTimeTextValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_startTimeTextValue.gridx = 3;
        gbc_startTimeTextValue.gridy = 5;
        contentPane.add(startTimeTextValue, gbc_startTimeTextValue);
        startTimeTextValue.setColumns(10);

        JLabel lblBasePeriod = new JLabel("Base period");
        GridBagConstraints gbc_lblBasePeriod = new GridBagConstraints();
        gbc_lblBasePeriod.insets = new Insets(0, 0, 5, 5);
        gbc_lblBasePeriod.gridx = 0;
        gbc_lblBasePeriod.gridy = 6;
        contentPane.add(lblBasePeriod, gbc_lblBasePeriod);

        GridBagConstraints gbc_basePeriodTextValue = new GridBagConstraints();
        gbc_basePeriodTextValue.insets = new Insets(0, 0, 5, 0);
        gbc_basePeriodTextValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_basePeriodTextValue.gridx = 3;
        gbc_basePeriodTextValue.gridy = 6;
        contentPane.add(basePeriodTextValue, gbc_basePeriodTextValue);
        basePeriodTextValue.setColumns(10);

        JLabel lblFillFactor = new JLabel("Fill factor");
        GridBagConstraints gbc_lblFillFactor = new GridBagConstraints();
        gbc_lblFillFactor.insets = new Insets(0, 0, 5, 5);
        gbc_lblFillFactor.gridx = 0;
        gbc_lblFillFactor.gridy = 7;
        contentPane.add(lblFillFactor, gbc_lblFillFactor);

        GridBagConstraints gbc_fillFactorTextValue = new GridBagConstraints();
        gbc_fillFactorTextValue.insets = new Insets(0, 0, 5, 0);
        gbc_fillFactorTextValue.fill = GridBagConstraints.HORIZONTAL;
        gbc_fillFactorTextValue.gridx = 3;
        gbc_fillFactorTextValue.gridy = 7;
        contentPane.add(fillFactorTextValue, gbc_fillFactorTextValue);
        fillFactorTextValue.setColumns(10);

        JLabel lblStepSampleNr = new JLabel("Step sample nr.");
        GridBagConstraints gbc_lblStepSampleNr = new GridBagConstraints();
        gbc_lblStepSampleNr.insets = new Insets(0, 0, 5, 5);
        gbc_lblStepSampleNr.gridx = 0;
        gbc_lblStepSampleNr.gridy = 8;
        contentPane.add(lblStepSampleNr, gbc_lblStepSampleNr);

        GridBagConstraints gbc_stepSampleNrText = new GridBagConstraints();
        gbc_stepSampleNrText.insets = new Insets(0, 0, 5, 0);
        gbc_stepSampleNrText.fill = GridBagConstraints.HORIZONTAL;
        gbc_stepSampleNrText.gridx = 3;
        gbc_stepSampleNrText.gridy = 8;
        contentPane.add(stepSampleNrText, gbc_stepSampleNrText);
        stepSampleNrText.setColumns(10);

        JLabel lblPosibility = new JLabel("Possibility");
        GridBagConstraints gbc_lblPosibility = new GridBagConstraints();
        gbc_lblPosibility.insets = new Insets(0, 0, 5, 5);
        gbc_lblPosibility.gridx = 0;
        gbc_lblPosibility.gridy = 9;
        contentPane.add(lblPosibility, gbc_lblPosibility);

        GridBagConstraints gbc_possibilityText = new GridBagConstraints();
        gbc_possibilityText.insets = new Insets(0, 0, 5, 0);
        gbc_possibilityText.fill = GridBagConstraints.HORIZONTAL;
        gbc_possibilityText.gridx = 3;
        gbc_possibilityText.gridy = 9;
        contentPane.add(possibilityText, gbc_possibilityText);
        possibilityText.setColumns(10);

        JLabel lblStepTime = new JLabel("Step time");
        GridBagConstraints gbc_lblStepTime = new GridBagConstraints();
        gbc_lblStepTime.insets = new Insets(0, 0, 5, 5);
        gbc_lblStepTime.gridx = 0;
        gbc_lblStepTime.gridy = 10;
        contentPane.add(lblStepTime, gbc_lblStepTime);

        GridBagConstraints gbc_stepTimeText = new GridBagConstraints();
        gbc_stepTimeText.anchor = GridBagConstraints.NORTH;
        gbc_stepTimeText.insets = new Insets(0, 0, 5, 0);
        gbc_stepTimeText.fill = GridBagConstraints.HORIZONTAL;
        gbc_stepTimeText.gridx = 3;
        gbc_stepTimeText.gridy = 10;
        contentPane.add(stepTimeText, gbc_stepTimeText);
        stepTimeText.setColumns(10);

        textFieldList = new ArrayList<JTextField>();
        textFieldList.add(amplitudeTextValue);
        textFieldList.add(signalTimeTextValue);
        textFieldList.add(startTimeTextValue);
        textFieldList.add(basePeriodTextValue);
        textFieldList.add(fillFactorTextValue);
        textFieldList.add(stepSampleNrText);
        textFieldList.add(possibilityText);
        textFieldList.add(stepTimeText);

        JComboBox signalTypeCB = new JComboBox(new String[]{"Sin", "UniformDistribution", "NormalDistribution", "Rectangle",
                "SinFR", "SinHR", "UnitImpulse", "UnitStep", "ImpulseNoise", "RectangularSymmetric", "Triangular","S3"});
        enableFields(signalTypeCB);
        signalTypeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jComboBox = (JComboBox) e.getSource();
                enableFields(jComboBox);
            }
        });
        GridBagConstraints gbc_signalTypeCB = new GridBagConstraints();
        gbc_signalTypeCB.insets = new Insets(0, 0, 5, 0);
        gbc_signalTypeCB.fill = GridBagConstraints.HORIZONTAL;
        gbc_signalTypeCB.gridx = 3;
        gbc_signalTypeCB.gridy = 11;
        contentPane.add(signalTypeCB, gbc_signalTypeCB);

        JButton btnGenerate = new GenerateButton("Generate", signalTypeCB, textFieldList);

        JButton signalDataButton = new DataDisplayButton("Signal data");
        GridBagConstraints gbc_signalDataButton = new GridBagConstraints();
        gbc_signalDataButton.insets = new Insets(0, 0, 5, 5);
        gbc_signalDataButton.gridx = 0;
        gbc_signalDataButton.gridy = 13;
        contentPane.add(signalDataButton, gbc_signalDataButton);
        GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
        gbc_btnGenerate.insets = new Insets(0, 0, 5, 0);
        gbc_btnGenerate.gridx = 3;
        gbc_btnGenerate.gridy = 13;
        contentPane.add(btnGenerate, gbc_btnGenerate);

        JButton btnHistogram = new HistogramButton();
        GridBagConstraints gbc_btnHistogram = new GridBagConstraints();
        gbc_btnHistogram.gridx = 3;
        gbc_btnHistogram.gridy = 14;
        contentPane.add(btnHistogram, gbc_btnHistogram);

        JLabel lblInterval = new JLabel("Bins");
        GridBagConstraints gbc_lblInterval = new GridBagConstraints();
        gbc_lblInterval.insets = new Insets(0, 0, 0, 5);
        gbc_lblInterval.gridx = 1;
        gbc_lblInterval.gridy = 14;
        contentPane.add(lblInterval, gbc_lblInterval);

        GridBagConstraints gbc_btnSlider = new GridBagConstraints();
        gbc_btnSlider.insets = new Insets(0, 0, 0, 5);
        gbc_btnSlider.gridx = 2;
        gbc_btnSlider.gridy = 14;
        contentPane.add(interval, gbc_btnSlider);
    }

    public void enableFields(JComboBox jComboBox) {
        boolean[] isAvailable = fieldsRule.get(jComboBox.getSelectedItem());

        for (int i = 0; i < availableFields.size(); i++) {
            if (isAvailable[i]) {
                availableFields.get(i).setEnabled(true);
            } else {
                availableFields.get(i).setEnabled(false);
            }
        }
    }
}
