package cps.lab.signal.generator;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;


public class RectangularGenerator implements SignalGenerator {

    private static final long serialVersionUID = 8798293450990984782L;
    private double A;
    private double d;
    private double t1;
    private double T;
    private double kw;

    public RectangularGenerator(double A, double d, double t1, double T, double kw) {
        super();
        this.A = A;
        this.d = d;
        this.t1 = t1;
        this.T = T;
        this.kw = kw;
    }

    @Override
    public Signal generateSignal(double frequency) {

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setBasePeriod(T);
        signal.setFillFactor(kw);
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.CONTINUOUS);

        ArrayList<Double> values = new ArrayList<>();

        int n2 = (int) (d * frequency + 1);
        XYSeries series = new XYSeries(n2);

        int n = (int) (d / T) + 1;
        double endTime = t1 + d;
        double t;
        double value;

        for (int k = 0; k < n; k++) {
            t = k * T + t1;

            if (t > endTime) {
                series.add(endTime, 0);
            } else {
                series.add(t, 0);
                series.add(t, A);

                t = kw * T + k * T + t1;
                if (t > endTime) {
                    series.add(endTime, A);
                } else {
                    series.add(t, A);
                    series.add(t, 0);
                }
            }
        }

        for (int i = 0; i < n2; i++) {
            t = i / frequency + t1;

            if ((t % T) >= (T * kw)) {
                value = 0;
            } else {
                value = A;
            }
            values.add(value);
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
        signal.setFillFactor(kw);
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.SAMPLE);

        ArrayList<Double> values = new ArrayList<>();

        int n2 = (int) (d * frequency + 1);

        double t;
        double value;

        XYSeries series = new XYSeries(n2);

        for (int i = 0; i < n2; i++) {
            t = i / frequency + t1;

            if ((t % T) >= (T * kw)) {
                value = 0;
            } else {
                value = A;
            }

            series.add(t, value);
            values.add(value);
        }

        signal.setValues(values);
        signal.setSeries(series);

        return signal;
    }

}
