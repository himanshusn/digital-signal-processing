package cps.lab.gui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import cps.lab.gui.buttons.ChoosePathButton;
import cps.lab.gui.buttons.LoadSignalButton;
import cps.lab.signal.Signal;

public class LoadWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1309491138979713168L;
	
	private JTextField pathTextField;
	private Signal signal;
	
	public LoadWindow() {
		this.signal = signal;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		pathTextField = new JTextField();
		GridBagConstraints gbc_pathTextField = new GridBagConstraints();
		gbc_pathTextField.insets = new Insets(0, 0, 5, 0);
		gbc_pathTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pathTextField.gridx = 2;
		gbc_pathTextField.gridy = 2;
		getContentPane().add(pathTextField, gbc_pathTextField);
		pathTextField.setColumns(10);
		
		JButton choosePathButton = new ChoosePathButton("Choose path",pathTextField);
		GridBagConstraints gbc_choosePathButton = new GridBagConstraints();
		gbc_choosePathButton.insets = new Insets(0, 0, 5, 5);
		gbc_choosePathButton.gridx = 1;
		gbc_choosePathButton.gridy = 2;
		getContentPane().add(choosePathButton, gbc_choosePathButton);
		
		
		JButton loadButton = new LoadSignalButton("Load",pathTextField,GeneratorWindow.currentSignal);
		GridBagConstraints gbc_loadButton = new GridBagConstraints();
		gbc_loadButton.insets = new Insets(0, 0, 0, 5);
		gbc_loadButton.gridx = 1;
		gbc_loadButton.gridy = 4;
		getContentPane().add(loadButton, gbc_loadButton);
		
		this.setBounds(100, 100, 200, 100);
	}


}
