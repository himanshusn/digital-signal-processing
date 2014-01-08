package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Transform;
import org.apache.commons.math3.complex.Complex;

/**
 * User: maciek
 * Date: 07.12.13
 * Time: 16:08
 */
public class FastCosineTransform extends Transform {

    private int bitCount;

    public FastCosineTransform(int bitCount) {
        super();
        this.bitCount = bitCount;
    }

    @Override
    protected Signal transformBody(Signal signal) {

        Complex[] input = signal.getComplexValues();

        int N = (int) Math.pow(2, bitCount);
//        int N = input.length;

        input = redefineInputSignalValues(input);

        Signal signalForFourier = new Signal();
        signalForFourier.setComplexValues(input);

        double a = Math.log(N)/Math.log(2);

        input = new FastFourierTransform((int) Math.floor(a)).transform(signalForFourier).getComplexValues();

        Complex[] output = new Complex[N];

        for (int m = 0; m < N; m++) {

            double argument = Math.PI * m / (2d * N);
            double cos = Math.cos(argument);
            double sin = Math.sin(argument);

            double X = c(m, N) * (cos * input[m].getReal() + sin * input[m].getImaginary());

            output[m] = new Complex(X, 0);

        }

        Signal result = new Signal();
        result.setFrequency(signal.getFrequency());
        result.setComplexValues(output);

        return result;
    }

    double c(int x, int N) {

        if (x == 0) {
            return Math.sqrt(1 / N);
        } else {
            return Math.sqrt(2 / N);
        }

    }

    public Complex[] redefineInputSignalValues(Complex[] input) {

        int N = input.length;

        Complex[] result = new Complex[N];
        int i = 0;

        for (int n = 0; n < N; n += 2, i++) {
            result[i] = input[n];
        }

        for (int n = N - 1; n > 0; n -= 2, i++) {
            result[i] = input[n];
        }

        return result;
    }

}
