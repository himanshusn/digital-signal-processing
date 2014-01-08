package cps.lab.signal.generator;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;
import org.apache.commons.math3.complex.Complex;
import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;


/**
 * User: maciek
 * Date: 20.10.13
 * Time: 12:03
 */
public class ImpulseNoiseGenerator implements SignalGenerator {

    private double A;
    private double d;
    private double t1;
    private double p;

    public ImpulseNoiseGenerator(double a, double d, double t1, double p) {
        this.A = a;
        this.d = d;
        this.t1 = t1;
        this.p = p;
    }

    @Override
    public Signal generateSignal(double frequency) {

        double random;

        double n2 = d * frequency;
        XYSeries series = new XYSeries(n2);
        ArrayList<Double> values = new ArrayList<Double>();

        List<Complex> complex = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            double t = t1 + i / frequency;
            random = Math.random();

            if (random < p) {
                series.add(t, A);
                values.add(A);
                complex.add(new Complex(A, 0));

            } else {
                series.add(t, 0);
                values.add(0.0);
                complex.add(new Complex(0, 0));
            }
        }

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setP(p);
        signal.setType(Signal.Type.CONTINUOUS);

        signal.setSignalGenerator(this);
        signal.setSeries(series);
        signal.setValues(values);
        signal.setComplexValues(complex.toArray(new Complex[complex.size()]));

        return signal;
    }

    @Override
    public Signal generateSamples(double frequency) {

        int n2 = (int) (d * frequency);
        ArrayList<Double> values = new ArrayList<>();
        double random;

        XYSeries series = new XYSeries(n2);

        for (int i = 0; i < n2; i++) {

            double t = t1 + i / frequency;
            random = Math.random();

            double value;

            if (random < p) {
                value = A;
            } else {
                value = 0;
            }
            values.add(value);
            series.add(t, value);
        }

        Signal signal = new Signal();

        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setP(p);
        signal.setType(Signal.Type.SAMPLE);
        signal.setSignalGenerator(this);
        signal.setValues(values);
        signal.setSeries(series);

        return signal;
    }
}
