package cps.lab.signal.operation.base;

import java.util.List;

import org.jfree.data.xy.XYDataItem;

import cps.lab.signal.Signal;
import cps.lab.utils.SignalUtils;

public abstract class Operation {
	
	abstract public Signal compute(Signal signal0 , Signal signal1) throws Exception;
	
	protected double cutSignals(Signal signal0,Signal signal1) throws Exception{
		double startTime = SignalUtils.caclulateStartTime(signal0, signal1);
		double endTime = SignalUtils.cacluclateEndTime(signal0, signal1);
		
		int signal0StartIndex = findTimeIndex(signal0, startTime);
		int signal1StartIndex = findTimeIndex(signal1, startTime);
		
		int signal0EndIndex = findTimeIndex(signal0, endTime);
		int signal1EndIndex = findTimeIndex(signal1, endTime);
		
		int itemCount = signal0.getSeries().getItemCount() -1;
		signal0.getSeries().delete(signal0EndIndex + 1, itemCount);
		signal0.getSeries().delete(0, signal0StartIndex - 1);
		
		itemCount = signal1.getSeries().getItemCount() -1;
		signal1.getSeries().delete(signal1EndIndex + 1, itemCount);
		signal1.getSeries().delete(0, signal1StartIndex - 1);
		
		return endTime - startTime;
	}
	
	private int findTimeIndex(Signal signal, double value) throws Exception{
		List<XYDataItem> data = signal.getSeries().getItems();
		
		for (XYDataItem xyDataItem : data) {
			if(xyDataItem.getXValue() == value){
				return data.indexOf(xyDataItem);
			}
		}
		throw new Exception("Frequency or time doesn't match");
//		return -1;
	}
	
	
}
