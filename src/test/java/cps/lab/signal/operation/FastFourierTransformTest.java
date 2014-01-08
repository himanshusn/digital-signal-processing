package cps.lab.signal.operation;

import static org.junit.Assert.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.SinusoidalGenerator;
import cps.lab.signal.operation.base.Transform;

import java.util.Arrays;

public class FastFourierTransformTest {

	Signal signal;
	Transform transformMaciek;
	Transform transformFast;
	
	@Before
	public void prepare(){
		transformFast = new FastCosineTransform(3);
		transformMaciek = new DiscreteCosineTransform(3);
//		signal = (new SinusoidalGenerator(1, 1, 0, 0.1)).generateSignal(7);
        Complex[] complexes = new Complex[]{new Complex(0, 0),
                new Complex(1, 0),
                new Complex(2, 0),
                new Complex(3, 0),
                new Complex(4, 0),
                new Complex(5, 0),
                new Complex(6, 0),
                new Complex(7, 0)};
        signal = new Signal();
        signal.setComplexValues(complexes);

	}
	
	
	
	@Test
	public void test() {
		Signal singalBasia = transformFast.transform(signal);
		Signal singalMaciek = transformMaciek.transform(signal);
//		
//		Assert.assertArrayEquals(singalBasia.getComplexValues(), singalMaciek.getComplexValues());
        System.out.println(Arrays.toString(singalBasia.getComplexValues()));
        System.out.println(Arrays.toString(singalMaciek.getComplexValues()));

    }
	
	public Complex W(int k, int N) 
	{
		return new Complex(Math.cos(2*Math.PI*k/N),-Math.sin(2*Math.PI*k/N));
	}
	
	private double core(int N, int n, int m) {
		return Math.exp(2 * Math.PI * m * n / N);
	}

}
