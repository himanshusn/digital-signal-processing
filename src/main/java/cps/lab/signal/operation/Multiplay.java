package cps.lab.signal.operation;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Operation;

public class Multiplay extends  Operation {

	@Override
	public Signal compute(Signal signal0, Signal signal1) throws Exception {
		double duration = this.cutSignals(signal0, signal1);
		int iterations = signal0.getSeries().getItemCount();
		XYSeries series = new XYSeries(iterations);
		for (int i=0;i<iterations;i++) {
			XYDataItem item0 = (XYDataItem) signal0.getSeries().getItems().get(i);
			XYDataItem item1 = (XYDataItem) signal1.getSeries().getItems().get(i);
			double yValue = item0.getYValue() * item1.getYValue();
			double xValue = item0.getXValue();
			series.add(xValue, yValue);
		}
		
		Signal signal = new Signal(series);
		signal.setSignalTime(duration);
		return signal;
	}

}
