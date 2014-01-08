package cps.lab.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.gui.windows.SaveWindow;
import cps.lab.signal.Signal;

public class SaveMenuItem extends JMenuItem implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6661647195417966666L;
	private SaveWindow saveWindow;
	
	
	public SaveMenuItem(String string) {
		super(string);
		this.addActionListener(this);
		saveWindow = new SaveWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(GeneratorWindow.currentSignal.getSignalGenerator() != null){
			saveWindow.getFrequencyValueText().setEditable(true);
			saveWindow.setVisible(true);
		}else{
			saveWindow.getFrequencyValueText().setText(GeneratorWindow.currentSignal.getSeries().getItemCount() +"");
			saveWindow.getFrequencyValueText().setEditable(false);
			saveWindow.setVisible(true);
		}
		
	}
	
	
	
}
