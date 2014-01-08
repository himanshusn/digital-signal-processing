package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Transform;
import org.apache.commons.math3.complex.Complex;

public class FastFourierTransform extends Transform {

	private int bitCount;

	public FastFourierTransform(int bitCount) {
		super();
		this.bitCount = bitCount;
	}

	@Override
	protected Signal transformBody(Signal signal) {

        int N = (int) Math.pow(2, bitCount);

        Complex[] complexValues = new Complex[N];
        for (int i = 0; i < N; i++) {
            Complex currentComplexValue = signal.getComplexValues()[i];
            complexValues[i] = new Complex(currentComplexValue.getReal(), currentComplexValue.getImaginary());
        }

        complexValues = inverse(complexValues);

        for (int n = 2; n <= N; n *= 2) {
//            System.out.println("\nn:" + n);

            Complex[] complexesTmp = new Complex[N];

            for (int i = 0; i < N; i += n) {

                for (int b = i; b < i + n; b++) {
//                    System.out.println("\t [" + b + "] = " + (b % (n / 2) + i) + " + core( " + (b % n) + ", " + n + ") * " + (b % (n / 2) + i + n / 2));

                    complexesTmp[b] = complexValues[b % (n / 2) + i].add(core((b % n), n).multiply(complexValues[b % (n / 2) + i + n / 2]));

                }

            }

            for (int i = 0; i < N; i++) {
                complexValues[i] = new Complex(complexesTmp[i].getReal(), complexesTmp[i].getImaginary());
            }
        }

        Complex[] result = new Complex[N];
        for (int i = 0; i < N; i++) {
            result[i] = new Complex(complexValues[i].getReal()/N, complexValues[i].getImaginary()/N);
        }

        Signal fourierTransform = new Signal();
        fourierTransform.setComplexValues(result);
        fourierTransform.setFrequency(signal.getFrequency());

        return fourierTransform;
    }

    private Complex[] inverse(Complex[] input) {

        Complex[] result = new Complex[input.length];
        for (int i = 0; i < input.length; i++) {
            Complex currentComplexValue = input[bitWiseInverse(i, bitCount)];
            result[i] = new Complex(currentComplexValue.getReal(), currentComplexValue.getImaginary());
        }

        return result;
    }

	private int bitWiseInverse(int value, int bitPosition) {
		int inverseValue = 0;

		for (int i = 0; i < bitPosition; i++) {
			boolean bit = (value & (1 << i)) > 0;
			if (bit) {
				inverseValue |= (1 << bitPosition - i - 1);
			} else {
				inverseValue &= ~(1 << bitPosition - i - 1);
			}
		}
		return inverseValue;
	}

	private Complex core(int k, int N) {
        double x = 2 * Math.PI * k / N;
        return new Complex(Math.cos(x), -Math.sin(x));
    }

}
