package cps.lab.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextField;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.signal.Signal;
import cps.lab.utils.GraphPanelSeter;

public class LoadSignalButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3365690887877717771L;

	private Signal signal;
	private JTextField textField;

	
	public LoadSignalButton(String name,JTextField textField,Signal signal) {
		super(name);
		this.signal = signal;
		this.textField = textField;
		this.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		File file = new File(textField.getText());

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			signal = (Signal) objectInputStream.readObject();
			GeneratorWindow.currentSignal = signal;
			GraphPanelSeter.drawGraph(signal, signal.getSignalTime());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
