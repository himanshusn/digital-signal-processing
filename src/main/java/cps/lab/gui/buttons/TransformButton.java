package cps.lab.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;

import cps.lab.gui.windows.GeneratorWindow;
import cps.lab.gui.windows.TransformPresentationWindow;
import cps.lab.gui.windows.TransformWindow.PresentationType;
import cps.lab.gui.windows.TransformWindow.TransformType;
import cps.lab.signal.Signal;
import cps.lab.signal.operation.*;
import cps.lab.signal.operation.base.Transform;

public class TransformButton extends JButton implements ActionListener{
	
	
	JComboBox<TransformType> transformComboBox;
	JComboBox<PresentationType> presentationComboBox;
	JSlider bitSlider;
	TransformPresentationWindow presentationWindow;

	
	
	public TransformButton(String name,JComboBox<TransformType> transformComboBox, JComboBox<PresentationType> presentationComboBox
			,JSlider bitSlider) {
		super(name);
		this.transformComboBox = transformComboBox;
		this.presentationComboBox = presentationComboBox;
		this.bitSlider = bitSlider;
		this.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		Transform fastTransform;
		Transform normalTransform;
		
		int N = (int)  Math.pow(2, bitSlider.getValue());
		double signalTime = GeneratorWindow.currentSignal.getSignalTime();
		Signal signal = GeneratorWindow.currentSignal.getSignalGenerator().generateSignal((N/signalTime));
		
		if((TransformType)transformComboBox.getSelectedItem() == TransformType.Fourier){
			fastTransform = new FastFourierTransform(bitSlider.getValue());
			normalTransform = new DiscreteFourierTransform(bitSlider.getValue());
			
		}else{
            normalTransform = new DiscreteCosineTransform(bitSlider.getValue());
            fastTransform = new FastCosineTransform(bitSlider.getValue());
		}
		
			fastTransform.transform(signal);
			Signal transformedSignal= normalTransform.transform(signal);
			System.out.println(fastTransform.getTransformTime());
			System.out.println(normalTransform.getTransformTime());
			presentationWindow = new TransformPresentationWindow(transformedSignal,
					(PresentationType) presentationComboBox.getSelectedItem(),
					 fastTransform.getTransformTime(),  normalTransform.getTransformTime(),signal.getBasePeriod());
			
			presentationWindow.setVisible(true);
		
		
	}
	
	
	
	

}
