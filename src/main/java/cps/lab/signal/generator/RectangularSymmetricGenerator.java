package cps.lab.signal.generator;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;
import org.jfree.data.Range;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;

import static org.apache.commons.lang.math.NumberUtils.max;
import static org.apache.commons.lang.math.NumberUtils.min;

public class RectangularSymmetricGenerator implements SignalGenerator {

    private static final long serialVersionUID = 8798293450990984782L;
    private double A;
    private double d;
    private double t1;
    private double T;
    private double kw;

    public RectangularSymmetricGenerator(double A, double d, double t1, double T, double kw) {
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

        ArrayList<Double> values = new ArrayList<Double>();

        int n2 = (int) (d * frequency + 1);
        XYSeries series = new XYSeries(n2);

        int n = (int) (d / T) + 1;
        double endTime = t1 + d;
        double t;
        double value;

        for (int k = 0; k < n; k++) {
            t = k * T + t1;

            if (t > endTime) {
                series.add(endTime, -A);
            } else {
                series.add(t, -A);
                series.add(t, A);

                t = kw * T + k * T + t1;
                if (t > endTime) {
                    series.add(endTime, A);
                } else {
                    series.add(t, A);
                    series.add(t, -A);
                }
//                values.add(A);
            }
        }

        for (int i = 0; i < n2; i++) {
            t = i / frequency + t1;

            if ((t % T) >= (T * kw)) {
                value = -A;
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
                value = -A;
            } else {
                value = A;
            }
            values.add(value);
            series.add(t, value);
        }

        signal.setValues(values);
        signal.setSeries(series);

        return signal;
    }

}
