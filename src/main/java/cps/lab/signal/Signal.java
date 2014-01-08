package cps.lab.signal;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.jfree.data.Range;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.XYSeries;

import cps.lab.signal.generator.base.SignalGenerator;

public class Signal implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4019883554109502968L;

    public enum Type {
        CONTINUOUS,
        DISCRETE,
        SAMPLE,
        QUANTIZED;
    }

    /**
     * Signal generator
     */
    private SignalGenerator signalGenerator;


    /**
     * Amplitude - A
     */
    private double amplitude;

    /**
     * Frequency - f
     */
    private double frequency;

    /**
     * Signal time - d
     */
    private double signalTime;

    /**
     * Start time - t1
     */
    private double startTime;

    /**
     * Base period - T
     */
    private double basePeriod;

    /**
     * Fill factor kw
     */
    private double fillFactor;

    /**
     * ts
     */
    private double ts;

    /**
     * ns
     */
    private int ns;

    /**
     * p - probability
     */
    private double p;

    /**
     * discrete signal - impulse
     */
    private Type type;

    /**
     * x y series for plot
     */
    private XYSeries series;

    /**
     *
     */
    private AbstractIntervalXYDataset dataset;

    /**
     * real y values
     */
    private List<Double> values;

    /**
     *
     */
    private Complex[] complexValues;

    public Signal() {
    }


    public Signal(XYSeries series) {
        this.series = series;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getSignalTime() {
        return signalTime;
    }

    public void setSignalTime(double signalTime) {
        this.signalTime = signalTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getBasePeriod() {
        return basePeriod;
    }

    public void setBasePeriod(double basePeriod) {
        this.basePeriod = basePeriod;
    }

    public XYSeries getSeries() {
        return series;
    }

    public void setSeries(XYSeries series) {
        this.series = series;
    }

    public double getFillFactor() {
        return fillFactor;
    }

    public void setFillFactor(double fillFactor) {
        this.fillFactor = fillFactor;
    }

    public double getTs() {
        return ts;
    }

    public void setTs(double ts) {
        this.ts = ts;
    }

    public double getLastSampleOrdinal() {
        return signalTime * frequency + 1;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public AbstractIntervalXYDataset getDataset() {
        return dataset;
    }

    public void setDataset(AbstractIntervalXYDataset dataset) {
        this.dataset = dataset;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(amplitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(basePeriod);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fillFactor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(frequency);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((series == null) ? 0 : series.hashCode());
        temp = Double.doubleToLongBits(signalTime);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(startTime);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Signal other = (Signal) obj;
        if (Double.doubleToLongBits(amplitude) != Double.doubleToLongBits(other.amplitude))
            return false;
        if (Double.doubleToLongBits(basePeriod) != Double.doubleToLongBits(other.basePeriod))
            return false;
        if (Double.doubleToLongBits(fillFactor) != Double.doubleToLongBits(other.fillFactor))
            return false;
        if (Double.doubleToLongBits(frequency) != Double.doubleToLongBits(other.frequency))
            return false;
        if (series == null) {
            if (other.series != null)
                return false;
        } else if (!series.equals(other.series))
            return false;
        if (Double.doubleToLongBits(signalTime) != Double.doubleToLongBits(other.signalTime))
            return false;
        if (Double.doubleToLongBits(startTime) != Double.doubleToLongBits(other.startTime))
            return false;
        return true;
    }

    public SignalGenerator getSignalGenerator() {
        return signalGenerator;
    }

    public void setSignalGenerator(SignalGenerator signalGenerator) {
        this.signalGenerator = signalGenerator;
    }

    public Complex[] getComplexValues() {
        return complexValues;
    }

    public void setComplexValues(Complex[] complexValues) {
        this.complexValues = complexValues;
    }
}
