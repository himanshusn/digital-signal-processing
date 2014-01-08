package cps.lab.fillter;

import cps.lab.fillter.windows.WindowFunction;
import cps.lab.utils.SignalUtils;
import org.apache.commons.math3.complex.Complex;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 02:23
 */
public class BandPassFilter extends Filter {

    public BandPassFilter() {

    }

    public BandPassFilter(int M, double K, int N, WindowFunction windowFunction) {
        super(M, K, N, windowFunction);
    }

    @Override
    public Filter build() {

        impulseResponse = new Complex[N];

        for (int n = 0; n < impulseResponse.length; n++) {

            if (n < M) {

                double value = SignalUtils.impulseResponseFunction(n, M, K);

                value *= 2 * Math.sin(Math.PI * n / 2);

                if (windowFunction != null) {

                    value *= windowFunction.value(n, M);

                }

                impulseResponse[n] = new Complex(value, 0);

            } else {

                impulseResponse[n] = new Complex(0, 0);

            }
        }

        return this;
    }

}
