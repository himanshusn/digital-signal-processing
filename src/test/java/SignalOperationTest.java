import static org.junit.Assert.fail;

import java.util.List;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.junit.Before;
import org.junit.Test;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.Add;
import cps.lab.signal.operation.base.Operation;


public class SignalOperationTest {

	Signal signal0;
	Signal signal1;
	Operation operation;
	@Before
	public void prepare(){
		
		XYSeries series0 = new XYSeries(2);
		XYSeries series1 = new XYSeries(2);
		
		series0.add(0, 10);
		series0.add(1, 10);
		series0.add(2, 10);
		
		series1.add(-1, 10);
		series1.add(0, 10);
		series1.add(1, 10);
		
		signal0 = new Signal(series0);
		signal1 = new Signal(series1);
		
	}
	
	@Test
	public void addTest() throws Exception {
		operation = new Add();
		Signal testSignal = operation.compute(signal0, signal1);
		List<XYDataItem> data = testSignal.getSeries().getItems();
		
		for (XYDataItem xyDataItem : data) {
			if(xyDataItem.getYValue() != 20){
				fail();
			}
		}
		
	}

}
