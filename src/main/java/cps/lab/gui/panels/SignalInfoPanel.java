package cps.lab.gui.panels;

import static cps.lab.utils.SignalUtils.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cps.lab.signal.Signal;
import cps.lab.signal.SimilarityMeasures;
import cps.lab.utils.SignalUtils;

public class SignalInfoPanel extends JPanel {

    private static final long serialVersionUID = -1040859128310720237L;

    private static final DecimalFormat df = new DecimalFormat("#.#######");

    private JPanel contentPane;
    private JTextField averageValueText;
    private JTextField averagePowerText;
    private JTextField absoluteAverageValueText;
    private JTextField effectiveValueText;
    private JTextField varianceText;

    private JTextField meanSquaredErrorText;
    private JTextField signalNoiseRatioText;
    private JTextField peakSignalNoiseRatioText;
    private JTextField maximumDifferenceText;
    private JTextField effectiveNumberOfBitsText;

    /**
     * Create the frame.
     */
    public SignalInfoPanel() {

        setBounds(100, 100, 350, 400);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 125, 0, 350, 0};
        gbl_contentPane.rowHeights = new int[]{0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gbl_contentPane);

        JLabel lblAverageValue = new JLabel("Average value");
        GridBagConstraints gbc_lblAverageValue = new GridBagConstraints();
        gbc_lblAverageValue.insets = new Insets(0, 0, 5, 5);
        gbc_lblAverageValue.gridx = 1;
        gbc_lblAverageValue.gridy = 1;
        add(lblAverageValue, gbc_lblAverageValue);

        averageValueText = new JTextField();
        averageValueText.setEditable(false);
        GridBagConstraints gbc_averageValueText = new GridBagConstraints();
        gbc_averageValueText.insets = new Insets(0, 0, 5, 0);
        gbc_averageValueText.fill = GridBagConstraints.HORIZONTAL;
        gbc_averageValueText.gridx = 3;
        gbc_averageValueText.gridy = 1;
        add(averageValueText, gbc_averageValueText);
        averageValueText.setColumns(10);

        JLabel lblAveragePower = new JLabel("Average power");
        GridBagConstraints gbc_lblAveragePower = new GridBagConstraints();
        gbc_lblAveragePower.insets = new Insets(0, 0, 5, 5);
        gbc_lblAveragePower.gridx = 1;
        gbc_lblAveragePower.gridy = 2;
        add(lblAveragePower, gbc_lblAveragePower);

        averagePowerText = new JTextField();
        averagePowerText.setEditable(false);
        GridBagConstraints gbc_averagePowerText = new GridBagConstraints();
        gbc_averagePowerText.insets = new Insets(0, 0, 5, 0);
        gbc_averagePowerText.fill = GridBagConstraints.HORIZONTAL;
        gbc_averagePowerText.gridx = 3;
        gbc_averagePowerText.gridy = 2;
        add(averagePowerText, gbc_averagePowerText);
        averagePowerText.setColumns(10);

        JLabel lblAbsoluteAverageValue = new JLabel("Absolute average value");
        GridBagConstraints gbc_lblAbsoluteAverageValue = new GridBagConstraints();
        gbc_lblAbsoluteAverageValue.insets = new Insets(0, 0, 5, 5);
        gbc_lblAbsoluteAverageValue.gridx = 1;
        gbc_lblAbsoluteAverageValue.gridy = 3;
        add(lblAbsoluteAverageValue, gbc_lblAbsoluteAverageValue);

        absoluteAverageValueText = new JTextField();
        absoluteAverageValueText.setEditable(false);
        GridBagConstraints gbc_absoluteAverageValueText = new GridBagConstraints();
        gbc_absoluteAverageValueText.insets = new Insets(0, 0, 5, 0);
        gbc_absoluteAverageValueText.fill = GridBagConstraints.HORIZONTAL;
        gbc_absoluteAverageValueText.gridx = 3;
        gbc_absoluteAverageValueText.gridy = 3;
        add(absoluteAverageValueText, gbc_absoluteAverageValueText);
        absoluteAverageValueText.setColumns(10);

        JLabel lblEffectiveValue = new JLabel("Effective value");
        GridBagConstraints gbc_lblEffectiveValue = new GridBagConstraints();
        gbc_lblEffectiveValue.insets = new Insets(0, 0, 5, 5);
        gbc_lblEffectiveValue.gridx = 1;
        gbc_lblEffectiveValue.gridy = 4;
        add(lblEffectiveValue, gbc_lblEffectiveValue);

        effectiveValueText = new JTextField();
        effectiveValueText.setEditable(false);
        GridBagConstraints gbc_effectiveValueText = new GridBagConstraints();
        gbc_effectiveValueText.insets = new Insets(0, 0, 5, 0);
        gbc_effectiveValueText.fill = GridBagConstraints.HORIZONTAL;
        gbc_effectiveValueText.gridx = 3;
        gbc_effectiveValueText.gridy = 4;
        add(effectiveValueText, gbc_effectiveValueText);
        effectiveValueText.setColumns(10);

        JLabel lblVariance = new JLabel("Variance");
        GridBagConstraints gbc_lblVariance = new GridBagConstraints();
        gbc_lblVariance.insets = new Insets(0, 0, 0, 5);
        gbc_lblVariance.gridx = 1;
        gbc_lblVariance.gridy = 5;
        add(lblVariance, gbc_lblVariance);

        varianceText = new JTextField();
        varianceText.setEditable(false);
        GridBagConstraints gbc_varianceText = new GridBagConstraints();
        gbc_varianceText.fill = GridBagConstraints.HORIZONTAL;
        gbc_varianceText.gridx = 3;
        gbc_varianceText.gridy = 5;
        add(varianceText, gbc_varianceText);
        varianceText.setColumns(10);

        JLabel lblMeanSquaredError = new JLabel("MSE (Mean Squared Error)");
        GridBagConstraints gbc_lblMeanSquaredError = new GridBagConstraints();
        gbc_lblMeanSquaredError.insets = new Insets(0, 0, 0, 5);
        gbc_lblMeanSquaredError.gridx = 1;
        gbc_lblMeanSquaredError.gridy = 7;
        add(lblMeanSquaredError, gbc_lblMeanSquaredError);

        meanSquaredErrorText = new JTextField();
        meanSquaredErrorText.setEditable(false);
        GridBagConstraints gbc_meanSquaredErrorText = new GridBagConstraints();
        gbc_meanSquaredErrorText.fill = GridBagConstraints.HORIZONTAL;
        gbc_meanSquaredErrorText.gridx = 3;
        gbc_meanSquaredErrorText.gridy = 7;
        add(meanSquaredErrorText, gbc_meanSquaredErrorText);
        meanSquaredErrorText.setColumns(10);

        JLabel lblSignalNoiseRatio = new JLabel("SNR (Signal to Noise Ratio)");
        GridBagConstraints gbc_lblSignalNoiseRatio = new GridBagConstraints();
        gbc_lblSignalNoiseRatio.insets = new Insets(0, 0, 0, 5);
        gbc_lblSignalNoiseRatio.gridx = 1;
        gbc_lblSignalNoiseRatio.gridy = 8;
        add(lblSignalNoiseRatio, gbc_lblSignalNoiseRatio);

        signalNoiseRatioText = new JTextField();
        signalNoiseRatioText.setEditable(false);
        GridBagConstraints gbc_signalNoiseRatioText = new GridBagConstraints();
        gbc_signalNoiseRatioText.fill = GridBagConstraints.HORIZONTAL;
        gbc_signalNoiseRatioText.gridx = 3;
        gbc_signalNoiseRatioText.gridy = 8;
        add(signalNoiseRatioText, gbc_signalNoiseRatioText);
        signalNoiseRatioText.setColumns(10);

        JLabel lblPeakSignalNoiseRatio = new JLabel("PSNR (Peak Signal to Noise Ratio)");
        GridBagConstraints gbc_lblPeakSignalNoiseRatio = new GridBagConstraints();
        gbc_lblPeakSignalNoiseRatio.insets = new Insets(0, 0, 0, 5);
        gbc_lblPeakSignalNoiseRatio.gridx = 1;
        gbc_lblPeakSignalNoiseRatio.gridy = 9;
        add(lblPeakSignalNoiseRatio, gbc_lblPeakSignalNoiseRatio);

        peakSignalNoiseRatioText = new JTextField();
        peakSignalNoiseRatioText.setEditable(false);
        GridBagConstraints gbc_peakSignalNoiseRatioText = new GridBagConstraints();
        gbc_peakSignalNoiseRatioText.fill = GridBagConstraints.HORIZONTAL;
        gbc_peakSignalNoiseRatioText.gridx = 3;
        gbc_peakSignalNoiseRatioText.gridy = 9;
        add(peakSignalNoiseRatioText, gbc_peakSignalNoiseRatioText);
        peakSignalNoiseRatioText.setColumns(10);

        JLabel lblMaximumDifference = new JLabel("MD (Maximum Difference)");
        GridBagConstraints gbc_lblMaximumDifference = new GridBagConstraints();
        gbc_lblMaximumDifference.insets = new Insets(0, 0, 0, 5);
        gbc_lblMaximumDifference.gridx = 1;
        gbc_lblMaximumDifference.gridy = 10;
        add(lblMaximumDifference, gbc_lblMaximumDifference);

        maximumDifferenceText = new JTextField();
        maximumDifferenceText.setEditable(false);
        GridBagConstraints gbc_maximumDifferenceText = new GridBagConstraints();
        gbc_maximumDifferenceText.fill = GridBagConstraints.HORIZONTAL;
        gbc_maximumDifferenceText.gridx = 3;
        gbc_maximumDifferenceText.gridy = 10;
        add(maximumDifferenceText, gbc_maximumDifferenceText);
        maximumDifferenceText.setColumns(10);

        JLabel lblEffectiveNumberOfBits = new JLabel("ENOB (Effective Number Of Bits)");
        GridBagConstraints gbc_lblEffectiveNumberOfBits = new GridBagConstraints();
        gbc_lblEffectiveNumberOfBits.insets = new Insets(0, 0, 0, 5);
        gbc_lblEffectiveNumberOfBits.gridx = 1;
        gbc_lblEffectiveNumberOfBits.gridy = 11;
        add(lblEffectiveNumberOfBits, gbc_lblEffectiveNumberOfBits);

        effectiveNumberOfBitsText = new JTextField();
        effectiveNumberOfBitsText.setEditable(false);
        GridBagConstraints gbc_effectiveNumberOfBitsText = new GridBagConstraints();
        gbc_effectiveNumberOfBitsText.fill = GridBagConstraints.HORIZONTAL;
        gbc_effectiveNumberOfBitsText.gridx = 3;
        gbc_effectiveNumberOfBitsText.gridy = 11;
        add(effectiveNumberOfBitsText, gbc_effectiveNumberOfBitsText);
        effectiveNumberOfBitsText.setColumns(10);
    }

    public void updateText(Signal signal) {
        absoluteAverageValueText.setText(df.format(averageAbsValue(signal)));
        averageValueText.setText(df.format(averageValue(signal)));
        averagePowerText.setText(df.format(averagePower(signal)));
        varianceText.setText(df.format(variance(signal)));
        effectiveValueText.setText(df.format(effectiveValue(signal)));
    }

    public void updateSimilarityMeasures(SimilarityMeasures similarityMeasures) {
        meanSquaredErrorText.setText(df.format(similarityMeasures.getMSE()));
        signalNoiseRatioText.setText(df.format(similarityMeasures.getSNR()));
        peakSignalNoiseRatioText.setText(df.format(similarityMeasures.getPSNR()));
        maximumDifferenceText.setText(df.format(similarityMeasures.getMD()));
        effectiveNumberOfBitsText.setText(df.format(similarityMeasures.getENOB()));
    }

}
