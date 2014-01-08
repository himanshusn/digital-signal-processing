package cps.lab.signal.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.jfree.data.xy.XYSeries;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;

public class S3Generator implements SignalGenerator{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3768342688403086643L;
	
	private double startTime;
	private double signalTime;
	private final double FREQUENCY = 16;
	
	public S3Generator(double startTime, double signalTime) {
		super();
		this.startTime = startTime;
		this.signalTime = signalTime;
	}

	@Override
	public Signal generateSignal(double frequency) {
		Signal signal = new Signal();

		signal.setStartTime(startTime);
		signal.setSignalTime(signalTime);
		signal.setSignalGenerator(this);
		signal.setFrequency(FREQUENCY);
		int n2 = (int) (FREQUENCY * signalTime);
		XYSeries series = new XYSeries(n2);
		
		double step = (startTime - signalTime) / n2;
		Complex[] complexValues = new Complex[n2];
		List<Double> values = new ArrayList<Double>();
		
		for (int i = 1; i <= n2; i++) {
			double t = step*i;
			double value = 5*Math.sin(2*Math.PI*t / 2)+Math.sin(2*Math.PI*t / 0.25);
			
			complexValues[i-1] = new Complex(value);
			values.add(value);
			series.add(t,value);
		}
		
		signal.setValues(values);
		signal.setComplexValues(complexValues);
		signal.setSeries(series);
		
		return signal;
	}

	@Override
	public Signal generateSamples(double frequency) {
		// TODO Auto-generated method stub
		return null;
	}

}
