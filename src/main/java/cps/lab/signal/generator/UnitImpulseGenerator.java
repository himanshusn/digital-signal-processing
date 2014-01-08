package cps.lab.signal.generator;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;
import org.jfree.data.Range;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;

import static org.apache.commons.lang.math.NumberUtils.max;
import static org.apache.commons.lang.math.NumberUtils.min;

/**
 * User: maciek
 * Date: 20.10.13
 * Time: 11:54
 */
public class UnitImpulseGenerator implements SignalGenerator {

    private double A;
    private double d;
    private double t1;
    private int ns;

    public UnitImpulseGenerator(double a, double d, double t1, int ns) {
        this.A = a;
        this.d = d;
        this.t1 = t1;
        this.ns = ns;
    }

    @Override
    public Signal generateSignal(double frequency) {

        double n2 = d * frequency;
        XYSeries series = new XYSeries(n2);
        ArrayList<Double> values = new ArrayList<Double>();

        for (int i = 0; i < n2; i++) {
            double t = t1 + i / frequency;
            double value;

            if (i + 1 == ns) {
                value = A;
            } else {
                value = 0;
            }
            series.add(t, value);
            values.add(value);
        }

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setNs(ns);
        signal.setType(Signal.Type.DISCRETE);

        signal.setSignalGenerator(this);
        signal.setSeries(series);
        signal.setValues(values);

        return signal;
    }

    @Override
    public Signal generateSamples(double frequency) {

        int n2 = (int) (d * frequency);
        ArrayList<Double> values = new ArrayList<>();

        XYSeries series = new XYSeries(n2);

        for (int i = 0; i < n2; i++) {
            double t = t1 + i / frequency;
            double value;

            if (i + 1 == ns) {
                value = A;
            } else {
                value = 0;
            }

            series.add(t, value);

            values.add(value);
        }

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setNs(ns);
        signal.setType(Signal.Type.SAMPLE);

        signal.setSignalGenerator(this);
        signal.setValues(values);
        signal.setSeries(series);

        return signal;
    }

}
