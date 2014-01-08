import static org.junit.Assert.fail;

import org.apache.commons.math3.complex.Complex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.SinusoidalGenerator;
import cps.lab.signal.operation.BasiaDiscreteFourierTransform;
import cps.lab.signal.operation.DiscreteFourierTransform;
import cps.lab.signal.operation.base.Transform;


public class FurierTest {

	
	Signal signal;
	Transform transformMaciek;
	Transform transformBasia;
	
	@Before
	public void prepare(){
		transformBasia = new BasiaDiscreteFourierTransform();
		transformMaciek = new DiscreteFourierTransform();
		signal = (new SinusoidalGenerator(1, 1, 0, 0.1)).generateSignal(1000);
		Complex[] complexValues = new Complex[signal.getValues().size()];
		
		for (int i = 0; i < complexValues.length; i++) {
			complexValues[i] = new Complex(signal.getValues().get(i));
		}
		signal.setComplexValues(complexValues);
	}
	
	
	
	@Test
	public void test() {
		Signal singalBasia = transformBasia.transform(signal);
		Signal singalMaciek = transformMaciek.transform(signal);
		
		Assert.assertArrayEquals(singalBasia.getComplexValues(), singalMaciek.getComplexValues());
	}

}
