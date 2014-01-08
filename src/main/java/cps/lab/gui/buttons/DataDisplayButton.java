package cps.lab.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.jfree.data.xy.XYDataItem;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.gui.windows.SignalDataDisplayWindow;

public class DataDisplayButton extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2778960124419962123L;
	
	private SignalDataDisplayWindow displayWindow;
	public DataDisplayButton(String text) {
		super(text);
		this.displayWindow = new SignalDataDisplayWindow();
		this.addActionListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("x y");
		List<XYDataItem> data = GeneratorWindow.currentSignal.getSeries().getItems();
		for (XYDataItem xyDataItem : data) {
			stringBuilder.append(" \n");
			stringBuilder.append(xyDataItem.getXValue());
			stringBuilder.append(" ");
			stringBuilder.append(xyDataItem.getYValue());
			stringBuilder.append(" ");
		}
		displayWindow.getTextArea().setText(stringBuilder.toString());
		displayWindow.setVisible(true);
	}

	
	
}
