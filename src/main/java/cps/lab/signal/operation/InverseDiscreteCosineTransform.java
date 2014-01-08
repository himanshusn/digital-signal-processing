package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Transform;
import org.apache.commons.math3.complex.Complex;

/**
 * User: maciek
 * Date: 07.12.13
 * Time: 16:08
 */
public class InverseDiscreteCosineTransform extends Transform {

    @Override
    protected Signal transformBody(Signal signal) {

        Complex[] input = signal.getComplexValues();

        int N = input.length;

        Complex[] output = new Complex[N];

        for (int n = 0; n < N; n++) {

            double x = 0;

            for (int m = 0; m < N; m++) {

                x += c(m, N) * input[m].getReal() * Math.cos(Math.PI * (2d * n + 1) * m / (2d * N));

            }

            output[n] = new Complex( x, 0);

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
