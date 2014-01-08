package cps.lab.signal.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.jfree.data.Range;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.XYSeries;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.base.SignalGenerator;

import static org.apache.commons.lang.math.NumberUtils.max;
import static org.apache.commons.lang.math.NumberUtils.min;

public class NormalDistributionGenerator implements SignalGenerator {

    private static final long serialVersionUID = -1867367862318744860L;

    private double A;
    private double d;
    private double t1;

    public NormalDistributionGenerator(double A, double d, double t1) {
        super();
        this.A = A;
        this.d = d;
        this.t1 = t1;
    }

    @Override
    public Signal generateSignal(double frequency) {

        Signal signal = new Signal();
        signal.setAmplitude(A);
        signal.setFrequency(frequency);
        signal.setSignalTime(d);
        signal.setStartTime(t1);
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.CONTINUOUS);

        NormalDistribution normalDistribution = new NormalDistribution();

        ArrayList<Double> values = new ArrayList<Double>();
        double n2 = signal.getLastSampleOrdinal();
        XYSeries series = new XYSeries(n2);
        List<Complex> complex = new ArrayList<>();

        for (int n = 0; n < n2; n++) {
            double t = n / frequency + t1;
            double y = normalDistribution.sample() * A;
            series.add(t, y);
            values.add(y);
            complex.add(new Complex(y, 0));
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
        signal.setSignalGenerator(this);
        signal.setType(Signal.Type.SAMPLE);

        NormalDistribution normalDistribution = new NormalDistribution();

        ArrayList<Double> values = new ArrayList<>();
        int n2 = (int) signal.getLastSampleOrdinal();

        XYSeries series = new XYSeries(n2);

        for (int n = 0; n < n2; n++) {
            double t = n / frequency + t1;
            double y = normalDistribution.sample() * A;
            values.add(y);
            series.add(t, y);
        }

        signal.setValues(values);
        signal.setSeries(series);

        return signal;
    }
}
