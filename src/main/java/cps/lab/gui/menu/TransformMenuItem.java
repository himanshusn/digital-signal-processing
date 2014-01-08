package cps.lab.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cps.lab.gui.windows.TransformWindow;

public class TransformMenuItem extends JMenuItem implements ActionListener {

	TransformWindow transformWindow;
	
	public TransformMenuItem(String text) {
		super(text);
		this.transformWindow = new TransformWindow();
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		transformWindow.setVisible(true);
	}
	
	

}
