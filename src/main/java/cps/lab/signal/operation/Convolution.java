package cps.lab.signal.operation;

import com.google.common.primitives.Doubles;
import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Operation;
import org.jfree.data.xy.XYSeries;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 16:50
 */
public class Convolution extends Operation {

    @Override
    public Signal compute(Signal signal0, Signal signal1) throws Exception {

        double[] h = Doubles.toArray(signal0.getValues());
        double[] x = Doubles.toArray(signal1.getValues());

        int M = h.length;
        int N = x.length;

        XYSeries series = new XYSeries(N + M - 1);
        double td = 1 / signal1.getFrequency();
        double t1 = signal1.getStartTime();

        double[] convolution = new double[N + M - 1];

        for (int n = 0; n < convolution.length; n++) {
            for (int k = 0, l = n - k; k < M; k++, l = n - k) {
                if (l >= 0 && l < N) {
                    convolution[n] += h[k] * x[l];
                }
            }
            series.add(t1 + td * n, convolution[n]);
        }

        Signal result = new Signal();
        result.setValues(Doubles.asList(convolution));
        result.setSeries(series);
        result.setType(Signal.Type.DISCRETE);

        return result;

    }

}
