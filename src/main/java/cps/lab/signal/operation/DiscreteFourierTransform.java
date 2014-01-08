package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Transform;
import org.apache.commons.math3.complex.Complex;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 17:09
 */
public class DiscreteFourierTransform extends Transform {

    private int bitCount;

    public DiscreteFourierTransform(int bitCount) {
        super();
        this.bitCount = bitCount;
    }

    @Override
    protected Signal transformBody(Signal signal) {

//        int N = signal.getComplexValues().length;
        int N = (int) Math.pow(2, bitCount);

        Complex[] input = new Complex[N];
        for (int i = 0; i < N; i++) {
            Complex currentComplexValue = signal.getComplexValues()[i];
            input[i] = new Complex(currentComplexValue.getReal(), currentComplexValue.getImaginary());
        }

        Complex[] output = new Complex[N];

        for (int k = 0; k < N; k++) {

            double re = 0;
            double im = 0;

            for (int t = 0; t < N; t++) {

                double x = 2 * Math.PI * t * k / N;
                double sin = Math.sin(x);
                double cos = Math.cos(x);

                re += input[t].getReal() * cos + input[t].getImaginary() * sin;
                im += -input[t].getReal() * sin + input[t].getImaginary() * cos;

            }

            output[k] = new Complex(re / N, im / N);
        }

        Signal result = new Signal();
        result.setFrequency(signal.getFrequency());
        result.setComplexValues(output);

        return result;
    }


}
