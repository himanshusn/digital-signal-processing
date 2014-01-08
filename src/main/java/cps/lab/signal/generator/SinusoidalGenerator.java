package cps.lab.signal.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.jfree.data.xy.XYSeries;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;

/**
 * (S3) sygnał sinusoidalny
 */
public class SinusoidalGenerator implements SignalGenerator {

    private static final long serialVersionUID = -1739821963017999322L;
    private double A;
    private double d;
    private double t1;
    private double T;

    public SinusoidalGenerator(double A, double d, double t1, double T) {
        super();
        this.A = A;
        this.d = d;
        this.t1 = t1;
        this.T = T;
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

        List<Complex> complex = new ArrayList<>();

        XYSeries series = new XYSeries(n2);

        for (int n = 0; n < n2; n++) {
            double t = n / frequency + t1;
            double y = A * Math.sin(((2 * Math.PI) / T) * (t - t1));
            series.add(t, y);
            values.add(y);
            complex.add(new Complex(y,0));
        }

        signal.setSeries(series);
        signal.setValues(values);
        signal.setComplexValues(complex.toArray(new Complex[complex.size()]));
        
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

        int n2 = (int) signal.getLastSampleOrdinal();

        ArrayList<Double> values = new ArrayList<>();

        XYSeries series = new XYSeries(n2);

        for (int n = 0; n < n2; n++) {
            double t = n / frequency + t1;
            double y = A * Math.sin(((2 * Math.PI) / T) * (t - t1));

            series.add(t, y);
            values.add(y);
        }

        signal.setSeries(series);
        signal.setValues(values);

        return signal;
    }

}
