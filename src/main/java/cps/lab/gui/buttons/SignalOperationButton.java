package cps.lab.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.signal.Signal;
import cps.lab.signal.operation.*;
import cps.lab.signal.operation.base.Operation;
import cps.lab.utils.GraphPanelSeter;
import cps.lab.utils.IOUtils;

public class SignalOperationButton extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 441908023216284865L;
	
	private JComboBox<String> signalOperationCB;
	private JTextField signal0Path;
	private JTextField signal1Path;
	
	
	public SignalOperationButton(String name,JComboBox<String> signalOperationCB, JTextField signal0, JTextField signal1) {
		super(name);
		this.signalOperationCB = signalOperationCB;
		this.signal0Path = signal0;
		this.signal1Path = signal1;
		this.addActionListener(this);
	}


    private final Operation[] operationTable = new Operation[]
            {new Add(), new Substrct(), new Multiplay(), new Divide(), new Convolution(), new Correlation(),
                    new CorrelationThroughConvolution()};

    @Override
	public void actionPerformed(ActionEvent e) {
		Signal signal0 = IOUtils.loadSignal(signal0Path.getText());
		Signal signal1 = IOUtils.loadSignal(signal1Path.getText());
		int index = signalOperationCB.getSelectedIndex();
		try {
            GeneratorWindow.currentSignal = operationTable[index].compute(signal0, signal1);
            GraphPanelSeter.drawGraph(GeneratorWindow.currentSignal, GeneratorWindow.currentSignal.getSignalTime());
            GraphPanelSeter.drawHistogram(GeneratorWindow.currentSignal, 10);
        } catch (Exception e1) {
			JOptionPane.showMessageDialog(null,
					"Sampling frequency or start time of signals doesn't match");
			e1.printStackTrace();
		}
	}
	
}
