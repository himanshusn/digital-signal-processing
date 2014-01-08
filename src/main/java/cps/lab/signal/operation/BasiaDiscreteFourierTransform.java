package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Transform;
import org.apache.commons.math3.complex.Complex;

/**
 * User: Marcin / Basia
 * Date: 30.11.13
 * Time: 15:09
 */
public class BasiaDiscreteFourierTransform extends Transform {

    @Override
    protected Signal transformBody(Signal signal) {

        Complex[] input = signal.getComplexValues();

        int n = input.length;

        Complex[] output = new Complex[n];

        for (int k = 0; k < n; k++) {

            double re = 0;
            double im = 0;

            for (int t = 0; t < n; t++) {

                double x = 2 * Math.PI * t * k / n;
                double sin = Math.sin(x);
                double cos = Math.cos(x);

                re += input[t].getReal() * cos;
                im += -input[t].getReal() * sin;

            }

            output[k] = new Complex(re, im);
        }

        Signal result = new Signal();
        result.setComplexValues(output);

        return result;
    }

}
