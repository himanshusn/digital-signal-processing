package cps.lab.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cps.lab.gui.windows.SignalOperationWindow;

public class SignalOperationMenuItem extends JMenuItem implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5504798150422252126L;

	private SignalOperationWindow signalOperationWindow;
	
	public SignalOperationMenuItem(String text) {
		super(text);
		this.addActionListener(this);
		signalOperationWindow = new SignalOperationWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		signalOperationWindow.setVisible(true);
	}

	
	
	
}
