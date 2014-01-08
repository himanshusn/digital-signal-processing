package cps.lab.signal.generator;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;
import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;


/**
 * User: maciek
 * Date: 20.10.13
 * Time: 15:06
 */
public class TriangularGenerator implements SignalGenerator {

    private double A;
    private double d;
    private double t1;
    private double T;
    private double kw;

    public TriangularGenerator(double a, double d, double t1, double t, double kw) {
        this.A = a;
        this.d = d;
        this.t1 = t1;
        this.T = t;
        this.kw = kw;
    }

    @Override
    public Signal generateSignal(double frequency) {

        ArrayList<Double> values = new ArrayList<Double>();

        int n2 = (int) (d * frequency + 1);
        XYSeries series = new XYSeries(n2);

        int n = (int) (d / T) + 1;
        double value;
        double t;

        for (int k = 0; k < n; k++) {
            t = t1 + k * T;
            series.add(t, 0);

            if (t + kw * T < t1 + d) {
                t = t + kw * T;
                series.add(t, A);

                if (t + (1 - kw) * T < t1 + d) {
                    t = t + (1 - kw) * T;
                    series.add(t, 0);
                } else {
                    t = t1 + d;
                    value = -A / (T * (1 - kw)) * (t - k * T - t1) + A / (1 - kw);
                    series.add(t, value);
                }
            } else {
                t = t1 + d;
                value = A / (kw * T) * (t - k * T - t1);
                series.add(t, value);
            }
        }

        //TODO
        for (int i = 0; i < n2; i++) {
            t = i / frequency + t1;

            n = (int) (t / T);

            if ((t % T) < (kw * T)) {
                value = (A / (kw * T)) * (t - (n * T));
            } else {
                value = ((-A / (T * (1 - kw))) * (t - (n * T))) + (A / (1 - kw));
            }
            values.add(value);
        }

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setBasePeriod(T);
        signal.setFillFactor(kw);
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.CONTINUOUS);

        signal.setSeries(series);
        signal.setValues(values);

        return signal;
    }

    @Override
    public Signal generateSamples(double frequency) {

        ArrayList<Double> values = new ArrayList<>();

        int n2 = (int) (d * frequency + 1);

        int n;
        double value;
        double t;

        XYSeries series = new XYSeries(n2);

        for (int i = 0; i < n2; i++) {
            t = i / frequency + t1;

            n = (int) (t / T);

            if ((t % T) < (kw * T)) {
                value = (A / (kw * T)) * (t - (n * T));
            } else {
                value = ((-A / (T * (1 - kw))) * (t - (n * T))) + (A / (1 - kw));
            }

            series.add(t, value);
            values.add(value);
        }

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setBasePeriod(T);
        signal.setFillFactor(kw);
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.SAMPLE);

        signal.setValues(values);
        signal.setSeries(series);

        return signal;

    }
}
