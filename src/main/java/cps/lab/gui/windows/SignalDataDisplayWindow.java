package cps.lab.gui.windows;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class SignalDataDisplayWindow extends JFrame {
	
	private JTextArea textArea;
	public SignalDataDisplayWindow() {
		
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public JTextArea getTextArea(){
		return textArea;
	}

}
