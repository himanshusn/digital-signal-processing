package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Transform;
import org.apache.commons.math3.complex.Complex;

/**
 * User: maciek
 * Date: 07.12.13
 * Time: 16:08
 */
public class DiscreteCosineTransform extends Transform {

    private int bitCount;

    public DiscreteCosineTransform(int bitCount) {
        super();
        this.bitCount = bitCount;
    }

    @Override
    protected Signal transformBody(Signal signal) {

        Complex[] input = signal.getComplexValues();

        int N = (int) Math.pow(2, bitCount);
//        int N = input.length;

        Complex[] output = new Complex[N];

        for (int m = 0; m < N; m++) {

            double X = 0;

            for (int n = 0; n < N; n++) {

                X += input[n].getReal() * Math.cos(Math.PI * (2d * n + 1) * m / (2d * N));

            }

            output[m] = new Complex(c(m, N) * X, 0);

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

}
