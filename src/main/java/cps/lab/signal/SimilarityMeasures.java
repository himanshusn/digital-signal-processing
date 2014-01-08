package cps.lab.signal;

import java.util.Collections;
import java.util.List;

/**
 * User: maciek
 * Date: 29.10.13
 * Time: 20:58
 */
public class SimilarityMeasures {

    /**
     * MSE - Mean Squered Error - Blad srednio-kwadratowy
     */
    private double MSE;

    /**
     * SNR - Signal to noise Ratio - Stosunek sygnalu do szumu
     */
    private double SNR;

    /**
     * PSNR - Peak Signal to noise Ratio - Szczytowy Stosunek sygnal - szum
     */
    private double PSNR;

    /**
     * MD - Maximum Difference - Maksymalna Roznica
     */
    private double MD;

    /**
     * ENOB - Effective Number of Bits - Efektywna liczba bitów
     */
    private double ENOB;

    public SimilarityMeasures build(List<Double> values, List<Double> valuesModified) {

        int N = Math.min(values.size(), valuesModified.size());
        double squaredSum = 0;
        double squaredSumSub = 0;
        double maxX = Collections.max(values);
        this.MD = Math.abs(values.get(0) - valuesModified.get(0));

        for (int i = 0; i < N; i++) {

            squaredSum += Math.pow(values.get(i), 2);

            squaredSumSub += Math.pow(values.get(i) - valuesModified.get(i), 2);

            double maxSubAbs = Math.abs(values.get(i) - valuesModified.get(i));
            if (maxSubAbs > this.MD) {
                this.MD = maxSubAbs;
            }
        }

        this.MSE = squaredSumSub / N;
        this.SNR = 10.0 * Math.log10(squaredSum / squaredSumSub);
        this.PSNR = 10.0 * Math.log10(maxX / this.MSE);
        this.ENOB = (SNR - 1.76) / 6.02;

        return this;
    }

    public double getMSE() {
        return MSE;
    }

    public void setMSE(double MSE) {
        this.MSE = MSE;
    }

    public double getSNR() {
        return SNR;
    }

    public void setSNR(double SNR) {
        this.SNR = SNR;
    }

    public double getPSNR() {
        return PSNR;
    }

    public void setPSNR(double PSNR) {
        this.PSNR = PSNR;
    }

    public double getMD() {
        return MD;
    }

    public void setMD(double MD) {
        this.MD = MD;
    }

    public double getENOB() {
        return ENOB;
    }

    public void setENOB(double ENOB) {
        this.ENOB = ENOB;
    }
}
