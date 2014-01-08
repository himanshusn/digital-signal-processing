import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import cps.lab.signal.Signal;
import cps.lab.signal.generator.NormalDistributionGenerator;
import cps.lab.utils.IOUtils;


public class IOUtilsTest {

	Signal signal;
	String path;
	
	@Before
	public void prepare(){
		signal = new NormalDistributionGenerator(1, 10, 0).generateSignal(150);
		path = "testSignal.test";
	}
	
	@Test
	public void iOTest() {
		IOUtils.saveSignal(path, signal);
		Signal loadedSignal = IOUtils.loadSignal(path);
		if(!loadedSignal.equals(signal)){
			fail();
		}
	}

}
