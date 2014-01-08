package cps.lab.fillter;

import cps.lab.fillter.windows.WindowFunction;
import cps.lab.signal.Signal;
import cps.lab.signal.operation.Convolution;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 01:54
 */
public abstract class Filter {

    /**
     * Rzad filtru
     */
    protected int M;

    /**
     * Dzielnik K, bo czestotliowsc_odciecia = czestotliwosc_probkowania / K
     * f0 = fd / K
     */
    protected double K;

    protected int N;

    protected WindowFunction windowFunction;

    protected Complex[] impulseResponse;

    public Filter() {

    }

    public Filter(int M, double K, int N, WindowFunction windowFunction) {
        this.K = K;
        this.M = M;
        this.N = N;
        this.windowFunction = windowFunction;
    }

    public abstract Filter build();

    public Signal filter(Signal signal) throws Exception {

        Signal signalA = new Signal();
        signalA.setValues(getImpulseRespnseReals());

        return new Convolution().compute(signalA, signal);

    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public double getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public WindowFunction getWindowFunction() {
        return windowFunction;
    }

    public void setWindowFunction(WindowFunction windowFunction) {
        this.windowFunction = windowFunction;
    }

    public Complex[] getImpulseResponse() {
        return impulseResponse;
    }

    public void setImpulseResponse(Complex[] impulseResponse) {
        this.impulseResponse = impulseResponse;
    }

    public List<Double> getImpulseRespnseReals() {
        List<Double> reals = new ArrayList<>();

        for (Complex c : impulseResponse) {
            reals.add(c.getReal());
        }

        return reals;
    }
}
