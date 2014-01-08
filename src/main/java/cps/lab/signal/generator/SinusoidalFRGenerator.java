package cps.lab.signal.generator;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;


public class SinusoidalFRGenerator implements SignalGenerator {

	private static final long serialVersionUID = 6609923614134871930L;
	private double A;
	private double d;
	private double t1;
	private double T;

	public SinusoidalFRGenerator(double a, double d, double t1, double t) {
		super();
		this.A = a;
		this.d = d;
		this.t1 = t1;
		this.T = t;
	}

    @Override
    public Signal generateSignal(double frequency) {

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setBasePeriod(T);
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.CONTINUOUS);

        ArrayList<Double> values = new ArrayList<Double>();

        double n2 = signal.getLastSampleOrdinal();
        XYSeries series = new XYSeries(n2);

        for (int n = 0; n < n2; n++) {
            double t = n / frequency + t1;
            double y = A * Math.abs(Math.sin(((2 * Math.PI) / T) * (t - t1)));
            series.add(t, y);
            values.add(y);
        }

        signal.setSeries(series);
        signal.setValues(values);

        return signal;
    }

    @Override
    public Signal generateSamples(double frequency) {

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setBasePeriod(T);
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.SAMPLE);

        ArrayList<Double> values = new ArrayList<>();

        int n2 = (int) signal.getLastSampleOrdinal();

        XYSeries series = new XYSeries(n2);

        for (int n = 0; n < n2; n++) {

            double t = n / frequency + t1;
            double y = A * Math.abs(Math.sin(((2 * Math.PI) / T) * (t - t1)));

            values.add(y);
            series.add(t, y);
        }

        signal.setValues(values);
        signal.setSeries(series);

        return signal;
    }
}
