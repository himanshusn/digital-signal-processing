package cps.lab.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cps.lab.gui.windows.LoadWindow;
import cps.lab.signal.Signal;

public class LoadMenuItem extends JMenuItem implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1141235351758889738L;
	private Signal signal;
	
	public LoadMenuItem(String string) {
		super(string);
		this.signal = signal;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		new LoadWindow().setVisible(true);
		
	}
	
	

}
