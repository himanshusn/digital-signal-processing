package cps.lab.signal.operation;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.SinusoidalGenerator;
import cps.lab.signal.operation.base.Transform;
import org.apache.commons.math3.complex.Complex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class FastFourierTransformTest2 {

	Signal signal;
	Transform transform;

	@Before
	public void prepare(){

		transform = new DiscreteFourierTransform();

        double[] arr = new double[]{0.7070,-0.7070, 0.0,  -1.999};

        Complex[] complexValues = new Complex[4];

        for (int i = 0; i < 4; i++) {
            complexValues[i] = new Complex(arr[i], 0);
        }

        signal = new Signal();
        signal.setComplexValues(complexValues);
	}

	@Test
	public void test() {

        System.out.println(Arrays.toString(signal.getComplexValues()));

        Signal signal1 = transform.transform(signal);

        System.out.println(Arrays.toString(signal1.getComplexValues()));

        Complex complex = new Complex(Math.cos(2 * Math.PI * 1/ 8), Math.sin(2 * Math.PI * 1 / 8));

        System.out.println(complex.multiply(1));
    }

}
