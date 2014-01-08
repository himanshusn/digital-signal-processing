package cps.lab.fillter;

import cps.lab.fillter.windows.WindowFunction;
import cps.lab.utils.SignalUtils;
import org.apache.commons.math3.complex.Complex;

/**
 * User: maciek
 * Date: 11.11.13
 * Time: 02:10
 */
public class LowPassFilter extends Filter {

    public LowPassFilter() {

    }

    public LowPassFilter(int M, double K, int N, WindowFunction windowFunction) {
        super(M, K, N, windowFunction);
    }

    /**
     * Generowanie odpowiedzi impulsowej
     */
    @Override
    public Filter build() {

        impulseResponse = new Complex[N];

        for (int n = 0; n < impulseResponse.length; n++) {

            if (n < M) {

                double value = SignalUtils.impulseResponseFunction(n, M, K);

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
