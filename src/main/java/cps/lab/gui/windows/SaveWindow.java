package cps.lab.gui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cps.lab.gui.buttons.ChoosePathButton;
import cps.lab.gui.buttons.SaveSignalButton;
import cps.lab.signal.Signal;

public class SaveWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7879928220480961984L;
	
	private JTextField textField;
	private JTextField frequencyValueText;
	
	public SaveWindow() {

        setBounds(50, 50, 400, 300);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{118, 316, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        JLabel frequencyLabel = new JLabel("Frequency");
        GridBagConstraints gbc_frequencyLabel = new GridBagConstraints();
        gbc_frequencyLabel.insets = new Insets(0, 0, 5, 5);
        gbc_frequencyLabel.gridx = 0;
        gbc_frequencyLabel.gridy = 1;
        getContentPane().add(frequencyLabel, gbc_frequencyLabel);

        frequencyValueText = new JTextField();
        GridBagConstraints gbc_frequencyValueText = new GridBagConstraints();
        gbc_frequencyValueText.insets = new Insets(0, 0, 5, 0);
        gbc_frequencyValueText.fill = GridBagConstraints.HORIZONTAL;
        gbc_frequencyValueText.gridx = 1;
        gbc_frequencyValueText.gridy = 1;
        getContentPane().add(frequencyValueText, gbc_frequencyValueText);
        frequencyValueText.setColumns(10);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 2;
        getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);

        JButton choosePathButton = new ChoosePathButton("Choose path", textField);
        GridBagConstraints gbc_choosePathButton = new GridBagConstraints();
        gbc_choosePathButton.insets = new Insets(0, 0, 5, 5);
        gbc_choosePathButton.gridx = 0;
        gbc_choosePathButton.gridy = 2;
        getContentPane().add(choosePathButton, gbc_choosePathButton);

        JButton saveButton = new SaveSignalButton("Save", textField, frequencyValueText);
        GridBagConstraints gbc_saveButton = new GridBagConstraints();
        gbc_saveButton.insets = new Insets(0, 0, 0, 5);
        gbc_saveButton.gridx = 0;
        gbc_saveButton.gridy = 5;
        getContentPane().add(saveButton, gbc_saveButton);
    }

    public JTextField getFrequencyValueText(){
		return frequencyValueText;
	}

}
