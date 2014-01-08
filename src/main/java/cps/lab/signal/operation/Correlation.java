package cps.lab.signal.operation;

import com.google.common.primitives.Doubles;
import cps.lab.signal.Signal;
import cps.lab.signal.operation.base.Operation;
import org.jfree.data.xy.XYSeries;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 16:54
 */
public class Correlation extends Operation {

    @Override
    public Signal compute(Signal signal0, Signal signal1) throws Exception {

        double[] h = Doubles.toArray(signal0.getValues());
        
        double[] x = Doubles.toArray(signal1.getValues());

        int M = h.length;
        int N = x.length;

        double[] corr = new double[N + M - 1];

        XYSeries series = new XYSeries(N + M - 1);
        double td = 1 / signal1.getFrequency();
        double t1 = signal1.getStartTime();

        int start = -(N - 1);
        int stop = corr.length + start;

        for (int n = start; n < stop; n++) {
            for (int k = 0, l = k - n; k < M; k++, l = k - n) {
                if (l >= 0 && l < N) {
                    corr[n - start] += h[k] * x[l];
                }
            }
            series.add(t1 + td * (n - start), corr[n - start]);
        }

        Signal result = new Signal();
        result.setValues(Doubles.asList(corr));
        result.setSeries(series);
        result.setType(Signal.Type.DISCRETE);

        return result;

    }
}
