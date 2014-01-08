package cps.lab.signal.operation;

import cps.lab.signal.generator.ComplexSignalGenerator;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Operation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Add extends Operation implements Serializable {



	@Override
	public Signal compute(Signal signal0, Signal signal1) throws Exception {
		double duration = this.cutSignals(signal0, signal1);
		int iterations = signal0.getSeries().getItemCount();
        XYSeries series = new XYSeries(iterations);

        List<Double> values = new ArrayList<>();

        for (int i=0;i<iterations;i++) {
			XYDataItem item0 = (XYDataItem) signal0.getSeries().getItems().get(i);
			XYDataItem item1 = (XYDataItem) signal1.getSeries().getItems().get(i);
			double yValue = item0.getYValue() + item1.getYValue();
			double xValue = item0.getXValue();
            series.add(xValue, yValue);
            values.add(yValue);
        }

        Signal signal = new Signal(series);
        signal.setSignalGenerator(new ComplexSignalGenerator(signal0,signal1,this));
        signal.setValues(values);
        signal.setSignalTime(duration);
        signal.setFrequency(values.size() / duration);

        return signal;
	}

}
