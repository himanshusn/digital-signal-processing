package cps.lab.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ChoosePathButton extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8403639906087119475L;

	private JTextField textField;
	
	public ChoosePathButton(String name,JTextField textField) {
		super(name);
		this.textField = textField;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);
		textField.setText(fileChooser.getSelectedFile().getPath());
	}

}
