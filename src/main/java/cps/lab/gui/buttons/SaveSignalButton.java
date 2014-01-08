package cps.lab.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JTextField;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.signal.Signal;

public class SaveSignalButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330413239940520929L;

	private JTextField textField;
	private JTextField textFrequency;

	public SaveSignalButton(String name, JTextField textField, JTextField textFrequency) {
		super(name);
		this.textField = textField;
		this.textFrequency = textFrequency;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File file = new File(textField.getText());
		double frequency = Double.parseDouble(textFrequency.getText());
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			if (GeneratorWindow.currentSignal.getSignalGenerator() != null) {
				objectOutputStream.writeObject(GeneratorWindow.currentSignal.getSignalGenerator().generateSignal(frequency));
			}else{
				objectOutputStream.writeObject(GeneratorWindow.currentSignal);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
