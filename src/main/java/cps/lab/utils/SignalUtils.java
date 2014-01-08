package cps.lab.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cps.lab.signal.Signal;
import org.apache.commons.math3.analysis.function.Sinc;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class SignalUtils {

    public static double averageValue(Signal signal) {
        List<Double> dataList = signal.getValues();
        double sum = 0;
        for (Double value : dataList) {
            sum += value.doubleValue();
        }
        return sum / dataList.size();
    }

    public static double averageAbsValue(Signal signal) {
        List<Double> dataList = signal.getValues();
        double sum = 0;
        for (Double value : dataList) {
            sum += Math.abs(value.doubleValue());
        }
        return sum / dataList.size();
    }

    public static double averagePower(Signal signal) {
        List<Double> dataList = signal.getValues();
        double sum = 0;
        for (Double value : dataList) {
            sum += value * value;
        }
        return sum / dataList.size();
    }

    public static double variance(Signal signal) {
        List<Double> dataList = signal.getValues();
        double sum = 0;
        double averageSignalValue = averageValue(signal);
        for (Double value : dataList) {
            double valueToPower = averageSignalValue - value;
            sum += valueToPower * valueToPower;
        }
        return sum / dataList.size();
    }

    public static double effectiveValue(Signal signal) {
        return Math.sqrt(averagePower(signal));
    }

    public static void checkFrequency(Signal signal1, Signal signal2) throws Exception {
        if (signal1.getFrequency() != signal2.getFrequency()) {
            throw new Exception("Frequency doesn't much");
        }
    }

    public static double caclulateStartTime(Signal signal0, Signal signal1) {
        List<Double> signals = new ArrayList<>();
        signals.add(signal0.getSeries().getMinX());
        signals.add(signal1.getSeries().getMinX());
        return Collections.max(signals);
    }

    public static double cacluclateEndTime(Signal signal0, Signal signal1) {
        List<Double> signals = new ArrayList<Double>();
        signals.add(signal0.getSeries().getMaxX());
        signals.add(signal1.getSeries().getMaxX());
        return Collections.min(signals);
    }

    public static Signal quantization(int bitsNumber, Signal inputSignal) {

        Signal resultSignal = new Signal();

        List<Double> values = inputSignal.getValues();

        XYSeries series = new XYSeries("Kwantyzacja równomierna z obcięciem");
        List<Double> valuesQ = new ArrayList<>();

        int representation = (int) Math.pow(2, bitsNumber);
        double max = Collections.max(values);
        double min = Collections.min(values);
        double step = (max - min) / (representation-1);

        System.out.println(max + " " + min + "" + step);

        for (int i = 0; i < inputSignal.getValues().size(); i++) {

            double a = inputSignal.getDataset().getStartYValue(0, i);
            double b = inputSignal.getDataset().getEndYValue(0, i);

            double y = (Math.abs(a) > Math.abs(b) ? a : b);
            y = step * (Math.floor(y / step) +  0.5);

            series.add(inputSignal.getDataset().getX(0, i), y);
            valuesQ.add(y);
        }

        resultSignal.setSignalTime(inputSignal.getSignalTime());
        resultSignal.setStartTime(inputSignal.getStartTime());
        resultSignal.setFrequency(inputSignal.getFrequency());
        resultSignal.setSeries(series);
        resultSignal.setValues(valuesQ);
        resultSignal.setType(Signal.Type.QUANTIZED);
        resultSignal.setDataset(new XYSeriesCollection(series));

        return resultSignal;
    }

    public static Signal sincFunctionReconstruction(double f, Signal signal) {

        List<Double> values = signal.getValues();
        double t1 = signal.getStartTime();
        double d = signal.getSignalTime();
        double f0 = signal.getFrequency();

        XYSeries reconstructedSeries = new XYSeries("Sygnal rekonstruowany funkcją sinc");
        List<Double> reconstructedValues = new ArrayList<>();

        double Ts = 1.0 / f0;
        double n2 = d * f0 + 1;

        Sinc sinc = new Sinc(true);

        for (double t = t1; t < d; t += 1/f){
            double value = 0;

            for (int n = 0; n < n2; n++) {
                value += values.get(n) * sinc.value(t / Ts - n);
            }

            reconstructedSeries.add(t, value);
            reconstructedValues.add(value);
        }

        Signal reconstructedSignal = new Signal();
        reconstructedSignal.setSeries(reconstructedSeries);
        reconstructedSignal.setValues(reconstructedValues);
        reconstructedSignal.setFrequency(f0);
        reconstructedSignal.setType(Signal.Type.CONTINUOUS);

        return reconstructedSignal;
    }

    public static Signal firstOrderHold(double f, Signal signal) {

        List<Double> values = signal.getValues();
        double t1 = signal.getStartTime();
        double f0 = signal.getFrequency();

        XYSeries reconstructedSeries = new XYSeries("Sygnal rekonstruowany FirstOrderHold");
        List<Double> reconstructedValues = new ArrayList<>();

//        double Ts = 1.0 / f;

        for (int k = 0; k < values.size() - 1; k++) {
            double ta = k / f0 + t1;
            double tb = (k + 1) / f0 + t1;
            double t = (tb - ta) / 2 + ta;

//            double v = values.get(k) + ((t - k * Ts) / Ts) * (values.get(k + 1) - values.get(k));
            double v = values.get(k) + (values.get(k + 1) - values.get(k)) / 2;

            reconstructedSeries.add(ta, values.get(k));
            reconstructedSeries.add(t, v);

            reconstructedValues.add(values.get(k));
            reconstructedValues.add(v);
        }

        Signal reconstructedSignal = new Signal();
        reconstructedSignal.setSeries(reconstructedSeries);
        reconstructedSignal.setValues(reconstructedValues);
        reconstructedSignal.setFrequency(f0);
        reconstructedSignal.setType(Signal.Type.CONTINUOUS);

        return reconstructedSignal;
    }

    public static double impulseResponseFunction(int n, int M, double K) {

        if (n == (M - 1) / 2) {

            return 2.0 / K;

        } else {

            double x = Math.PI * (n - (M - 1) / 2);
            return Math.sin(2 * x / K) / x;

        }
    }

    public static Signal makePhase(Signal signal, int n) {

        Signal result = new Signal();
        result.setSignalTime(signal.getSignalTime());
        result.setStartTime(signal.getStartTime());
        result.setFrequency(signal.getFrequency());

        List<Double> values = new ArrayList<>();

        for (int i = n; i < signal.getValues().size(); i++) {
            values.add(signal.getValues().get(i));
        }

        for (int i = 0; i < n; i++) {
            values.add(signal.getValues().get(i));
        }

        result.setValues(values);

        return result;
    }

    public static Signal extendSignalDuration(Signal signal, int times) {

        Signal result = new Signal();

        List<Double> values = new ArrayList<>();
        for (int n = 0; n < times; n++) {
            values.addAll(signal.getValues());
        }
        result.setValues(values);
        result.setFrequency(signal.getFrequency());
        result.setSignalTime(signal.getSignalTime() * times);

        return result;
    }

}
