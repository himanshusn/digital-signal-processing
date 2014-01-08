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
 * Time: 11:21
 */
public class UnitStepGenerator implements SignalGenerator {

    private double A;
    private double d;
    private double t1;
    private double ts;

    public UnitStepGenerator(double a, double d, double t1, double ts) {
        super();
        this.A = a;
        this.d = d;
        this.t1 = t1;
        this.ts = ts;
    }

    @Override
    public Signal generateSignal(double frequency) {

        ArrayList<Double> values = new ArrayList<Double>();
        double n2 = d * frequency;

        for (int n = 0; n < n2; n++) {
            double t = n / frequency + t1;

            double value;
            if (t < ts) {
                value = 0;
            } else if (t == ts) {
                value = 0.5 * A;
            } else {
                value = A;
            }
            values.add(value);
        }

        XYSeries series = new XYSeries(4);
        series.add(t1, 0);
        series.add(ts, 0);
        series.add(ts, A);
        series.add(t1 + d, A);

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setTs(ts);
        signal.setType(Signal.Type.CONTINUOUS);

        signal.setSignalGenerator(this);
        signal.setSeries(series);
        signal.setValues(values);

        return signal;
    }

    @Override
    public Signal generateSamples(double frequency) {

        ArrayList<Double> values = new ArrayList<>();
        int n2 = (int) (d * frequency);

        XYSeries series = new XYSeries(n2);

        for (int n = 0; n < n2; n++) {

            double t = n / frequency + t1;

            double value;
            if (t < ts) {
                value = 0;
            } else if (t == ts) {
                value = 0.5 * A;
            } else {
                value = A;
            }

            values.add(value);
            series.add(t, value);
        }

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setTs(ts);
        signal.setType(Signal.Type.SAMPLE);
        signal.setSignalGenerator(this);
        signal.setValues(values);
        signal.setSeries(series);

        return signal;
    }
}
